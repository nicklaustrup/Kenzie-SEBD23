package com.kenzie.trees.library;

import com.kenzie.trees.library.treestructure.BookNode;

import org.apache.commons.lang.NotImplementedException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Application to test traversing Binary Trees and Binary Search Trees.
 *
 * Represents a Library with a collection of books.
 */
public class LibraryService {

    /**
     * The root node of our tree of Books.
     * Assume this tree is sorted by ISBN.
     * The tree and its nodes should not be modified by our application.
    */
    private final BookNode books;

    /**
     * Constructs our library with a default tree of books.
     * Assume this tree is sorted by ISBN.
     *
     * @param books The root node of a tree of Books
     */
    public LibraryService(final BookNode books) {
        this.books = books;
    }

    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given ISBN.
     *
     * @param isbn A given ISBN to search our library for
     * @return True if a book with the given ISBN is in our library and
     *         false otherwise
     */
    public boolean isBookInLibraryByIsbn(String isbn) {
        // PARTICIPANTS: IMPLEMENT YOUR BINARY SEARCH HERE
        if (isbn == null || isbn.isEmpty()) {
            return false;
        }

        BookNode pointer = books;

        while (pointer != null) {
            if (pointer.getBook().getIsbn().equals(isbn)) {
                return true;
            }
            if (pointer.getBook().getIsbn().compareTo(isbn) > 0) {
                pointer = pointer.getLeft();
            }
            if (pointer.getBook().getIsbn().compareTo(isbn) < 0) {
                pointer = pointer.getRight();
            }
        }

        return false;
    }


    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given Title and Author.
     *
     * @param title A given title to search our library for, alongside an author's name
     * @param author The name of a given author to search our library for, alongside a title
     * @return True if a book with the given title and author is in our library, and
     *         false otherwise
     */
    public boolean isBookInLibraryByTitleAndAuthor(String title, String author) {
        // PARTICIPANTS: IMPLEMENT YOUR DEPTH FIRST SEARCH HERE

        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            return false;
        }

        if (books == null) {
            return false;
        }

        Stack<BookNode> stack = new Stack<>();

        stack.push(books);

        while (!stack.isEmpty()) {
            BookNode node = stack.pop();
            if (node.getBook().getTitle().equals(title) && node.getBook().getAuthor().equals(author)) {
                return true;
            }
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }

        return false;
    }

}
