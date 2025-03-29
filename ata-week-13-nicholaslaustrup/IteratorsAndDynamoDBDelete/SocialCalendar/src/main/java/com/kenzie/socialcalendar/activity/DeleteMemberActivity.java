package com.kenzie.socialcalendar.activity;

import com.kenzie.socialcalendar.dao.InviteDao;
import com.kenzie.socialcalendar.dao.MemberDao;
import com.kenzie.socialcalendar.dao.models.Invite;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Handles a request to delete a member. Actually deletes the member
 * from datastore for privacy reasons.
 */
public class DeleteMemberActivity {
    private MemberDao memberDao;
    private InviteDao inviteDao;

    /**
     * Constructs the activity handler.
     * @param memberDao The MemberDao to use for member item operations in Phase 1
     * @param inviteDao The InviteDao to use for managing invites in Phase 2
     */
    @Inject
    public DeleteMemberActivity(MemberDao memberDao,
                                InviteDao inviteDao) {
        this.memberDao = memberDao;
        this.inviteDao = inviteDao;
    }

    /**
     * Handles a request to delete a member permanently, by member ID.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param memberId The ID of the member to delete
     */
    public void handleRequest(final String memberId) {
        int index = 0;

        List<Invite> inviteList = inviteDao.getInvitesSentToMember(memberId);
        ListIterator<Invite> inviteListIterator = inviteList.listIterator();
        while (inviteListIterator.hasNext()) {
            inviteListIterator.next();

            //get the invite ID from the invite list
            String inviteId = inviteList.get(index).getEventId();

            //Delete the invite from DB
            inviteDao.deleteInvite(inviteId, memberId);
//            memberDao.deletePermanently(memberId);

            //this line may be inconsequential
//            inviteListIterator.remove();

            //Increment the index
            index++;
        }
        memberDao.deletePermanently(memberId);
    }
}
