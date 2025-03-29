package com.kenzie.classes;

import java.util.ArrayList;

class ToDoItem {
    //class properties
    public String description = "";
    public String priority = "";
    public boolean isDone = false;

    //constructors
    public ToDoItem () {
    }

    public ToDoItem (String description, String priority) {
        this.isDone = false;
        this.description = description;
        this.priority = priority;
    }

    //Methods

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getPriority() {
        return this.priority;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    public boolean getIsDone () {
        return this.isDone;
    }
}

    public class ToDoList {
        public String listName;
        public int maxItems;
        public ArrayList<ToDoItem> itemList;


        //constructors
        public ToDoList() {
            this.listName = "";
            this.maxItems = 10;
            this.itemList = new ArrayList<ToDoItem>();
        }

        public ToDoList(String listName, int maxItems) {
            this.listName = listName;
            this.maxItems = maxItems;
            this.itemList = new ArrayList<>();
        }

        public String getListName() {
            return listName;
        }

        public void setListName(String listName) {
            this.listName = listName;
        }

        public int getMaxItems() {
            return maxItems;
        }

        public void setMaxItems(int maxItems) {
            this.maxItems = maxItems;
        }

        public ArrayList<ToDoItem> getItemList() {
            return itemList;
        }

        public void setItemList(ArrayList<ToDoItem> itemList) {
            this.itemList = itemList;
            this.itemList.addAll(itemList);
        }
    }
