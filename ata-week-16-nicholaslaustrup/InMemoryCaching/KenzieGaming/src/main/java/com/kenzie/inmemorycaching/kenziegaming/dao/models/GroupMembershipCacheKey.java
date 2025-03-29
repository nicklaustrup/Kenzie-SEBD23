package com.kenzie.inmemorycaching.kenziegaming.dao.models;

import java.util.Objects;

public final class GroupMembershipCacheKey {

    private String userId;
    private String groupId;
//    private GroupType groupType;

    public GroupMembershipCacheKey(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return this.userId;
    }
    public String getGroupId() {
        return this.groupId;
    }
//    public GroupType getGroupType() {
//        return groupType;
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()){
            return false;
        }
        GroupMembershipCacheKey that = (GroupMembershipCacheKey) o;

        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getGroupId(), that.getGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getGroupId());
    }
}
