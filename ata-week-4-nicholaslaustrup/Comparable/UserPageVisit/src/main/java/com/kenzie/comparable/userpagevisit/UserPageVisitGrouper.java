package com.kenzie.comparable.userpagevisit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class provides grouping functionality for UserPageVisits that is used by UserPageVisitAggregator.
 * The only grouping functionality that's currently available is based on UserPageVisit natural order.
 */
public class UserPageVisitGrouper {
    /**
     * Returns a sorted list of UserPageVisits based on their natural order. Original list is not modified.
     * @param userPageVisits List of UserPageVisits
     * @return sorted list of userPageVisits
     */
    public List<UserPageVisit> groupUserPageVisits(List<UserPageVisit> userPageVisits) {
        // PARTICIPANTS: add logic to implement groupUserPageVisits here
        List<UserPageVisit> userPageVisitsCopy = new ArrayList<>(userPageVisits);
        Collections.sort(userPageVisitsCopy);
        return userPageVisitsCopy;
    }
}
