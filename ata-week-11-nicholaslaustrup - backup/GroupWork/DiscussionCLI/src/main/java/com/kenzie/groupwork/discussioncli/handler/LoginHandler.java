package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.dynamodb.Member;
import com.kenzie.groupwork.discussioncli.dynamodb.MemberDao;
import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;

import javax.inject.Inject;

/**
 * Handles the request to login (or create) a member.
 */
public class LoginHandler implements DiscussionCliOperationHandler {
    private MemberDao memberDao;
    private ATAUserInput userHandler;
    private DiscussionCliState state;

    /**
     * Constructs a new LoginHandler with provided DAO.
     * @param memberDao the DAO for accessing members
     * @param userHandler Handler to manage user interactions
     */
    @Inject
    public LoginHandler(MemberDao memberDao, ATAUserInput userHandler, DiscussionCliState state) {
        this.memberDao = memberDao;
        this.userHandler = userHandler;
        this.state = state;
    }

    @Override
    public String handleRequest() {
        String username = requestUsername();
        Member member = findOrCreateMember(username);

        state.setCurrentMember(member);
        state.setNextOperation(DiscussionCliOperation.VIEW_TOPICS);

        return renderResponse(member);
    }

    private String requestUsername() {
        return userHandler.getString("", "username: ");
    }

    private Member findOrCreateMember(String username) {
        Member member = memberDao.getMember(username);

        if (null == member) {
            member = new Member(username);
            memberDao.createMember(member);
        }

        return member;
    }

    private String renderResponse(Member member) {
        return String.format("Welcome, %s!", member.display());
    }
}
