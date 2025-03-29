package com.kenzie.linkedlist.lunchclub;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class LunchClub {
    private LinkedList<String> membersInLine;

    public LunchClub(){
        membersInLine = new LinkedList<>();
    }

    public LunchClub(LinkedList<String> membersList){
        membersInLine = membersList;
    }

    // -- Simulates someone entering the back of the line for lunch.
    public void addMemberToLine(String member){
        // TODO: add Member to membersInLine on the next line
        membersInLine.addLast(member);
    }

    //-- Simulates a group of people entering and adding to the back of the line.
    public void addGroupToLine(List<String> group) {
        // TODO: add a group of Members to membersInLine on the next line
        membersInLine.addAll(group);
    }

    //-- Removes a person from the front of the lunch line to serve them food.
    // do not attempt to remove a person if membersInLine is empty
    public String serveMember(){
        try{
            // TODO: remove First Member from membersInLine to replace the next line of code
            return membersInLine.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    // --Returns the List of members representing the current state of the lunch line.
    public LinkedList<String> getMembersInLine() {
        // TODO: return membersInLine;
        return membersInLine;
    }
}