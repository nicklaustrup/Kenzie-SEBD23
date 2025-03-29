import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CommentClient from "../api/commentClient.js";

class CommentPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetComments', 'onCreate', 'renderComments'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers
     */
    async mount() {
        document.getElementById('create-comment-form').addEventListener('submit', this.onCreate);
        this.client = new CommentClient();
        this.dataStore.addChangeListener(this.renderComments);
        await this.client.getAllComments();
    }

    /** Render Methods -----------------------------------------------------------------------------------------------*/

    async renderComments() {
        let resultArea = document.getElementById("result-comment-info");
        let commentHTML = "<ul>";

        const comments = this.dataStore.get("comments");

        if (comments) {
            for (let comment of comments) {
            commentHTML += `<li>
                <h3>${comment.title}</h3>
                <h4>${comment.owner}</h4>
                <p>${comment.content}</p>
                </li>`;
        }
            commentHTML += "</ul>";
            resultArea.innerHTML = commentHTML;
        } else {
            resultArea.innerHTML = "No Comments";
        }
    }

    /** Event Handlers -----------------------------------------------------------------------------------------------*/

    async onGetComments() {
        // Prevent the page from refreshing on form submit

        let result = await this.client.getAllComments(this.errorHandler);
        this.dataStore.set("comments", result);
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let owner = document.getElementById("create-comment-owner").value;
        let title = document.getElementById("create-comment-title").value;
        let comment = document.getElementById("create-comment-content").value;

        const createdComment = await this.client.createComment(title, owner, comment, this.errorHandler);

        if (createdComment) {
            this.showMessage(`Posted a comment by ${createdComment.owner}!`);
            await this.onGetComments();
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const commentPage = new CommentPage();
    await commentPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
