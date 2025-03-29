package com.kenzie.app;

import java.util.Objects;

public class EntryPass {
    //Class properties
    // TODO: Fill in the properties
    private String name;
    private String id;
    private String accessLevel;


    //Class constructors
    // TODO: Fill in the constructors

    public EntryPass(String name, String id) {
        this.name = name;
        this.id = id;
        this.accessLevel = "general";
    }

    public EntryPass(String name, String id, String accessLevel) {

        this.name = name;
        this.id = id;
        this.accessLevel = accessLevel;
    }

    //Class methods
    // TODO: Fill in the methods
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevel() {
        return this.accessLevel;
    }


    //Override equals
    @Override
    public boolean equals(Object entryPass) {
        if (entryPass instanceof EntryPass) {
            EntryPass that = (EntryPass) entryPass;


            String accessLevel = this.getAccessLevel();
            String id = this.getID();
            String expectedID = that.getID();
            String expectedAccessLevel = that.getAccessLevel();


            return id.equalsIgnoreCase(expectedID) && accessLevel.equalsIgnoreCase(expectedAccessLevel);
        }
        return false;
    }
}


