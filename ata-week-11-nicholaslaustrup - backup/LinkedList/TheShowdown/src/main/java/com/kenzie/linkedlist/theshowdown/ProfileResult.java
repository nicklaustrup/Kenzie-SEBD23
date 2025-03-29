package com.kenzie.linkedlist.theshowdown;

public class ProfileResult {
    private String listType;
    private String dataSize;
    private String addFirstTime;
    private String addLastTime;
    private String getFirstTime;
    private String clearTime;
    private String getMiddleTime;

    public String getListType() {
        return listType;
    }

    public void setListType(final String listType) {
        this.listType = listType;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize.toString();
    }

    public String getDataSize() {
        return this.dataSize;
    }

    public String getAddFirstTime() {
        return addFirstTime;
    }

    public void setAddFirstTime(final String addFirstTime) {
        this.addFirstTime = addFirstTime;
    }

    public String getAddLastTime() {
        return addLastTime;
    }

    public void setAddLastTime(final String addLastTime) {
        this.addLastTime = addLastTime;
    }

    public String getGetFirstTime() {
        return getFirstTime;
    }

    public void setGetFirstTime(final String getFirstTime) {
        this.getFirstTime = getFirstTime;
    }

    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime;
    }

    public String getGetMiddleTime() {
        return getMiddleTime;
    }

    public void setGetMiddleTime(final String getMiddleTime) {
        this.getMiddleTime = getMiddleTime;
    }

}
