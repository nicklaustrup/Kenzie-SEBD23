package com.kenzie.linkedlist.lunchclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LunchClubTest {
    private LunchClub lunchClub;
    private LinkedList<String> testMembers = new LinkedList<>();

    @BeforeEach
    public void setup() {
        testMembers.addFirst("John"); // last in line
        testMembers.addFirst("James"); // second in line
        testMembers.addFirst("Jane"); // first in line
        lunchClub = new LunchClub(testMembers);
    }

    @Test
    public void lunchClub_getMembersInLine_returnsCorrectOrder() {
        try {
            assertEquals("Jane", lunchClub.getMembersInLine().get(0));
            assertEquals("James", lunchClub.getMembersInLine().get(1));
            assertEquals("John", lunchClub.getMembersInLine().get(2));
        } catch(IndexOutOfBoundsException e){
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void lunchClub_addMemberToLine_returnsCorrectOrder() {
        lunchClub.addMemberToLine("Jake");
        try {
            assertEquals("Jane", lunchClub.getMembersInLine().get(0));
            assertEquals("James", lunchClub.getMembersInLine().get(1));
            assertEquals("John", lunchClub.getMembersInLine().get(2));
            assertEquals("Jake", lunchClub.getMembersInLine().get(3));
        } catch(IndexOutOfBoundsException e){
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void lunchClub_addGroupToLine_returnsCorrectOrder() {
        List<String> memberList = new ArrayList<>();
        memberList.add("William");
        memberList.add("Wilma");
        memberList.add("Warren");
        lunchClub.addGroupToLine(memberList);
        try {
            assertEquals("Jane", lunchClub.getMembersInLine().get(0));
            assertEquals("James", lunchClub.getMembersInLine().get(1));
            assertEquals("John", lunchClub.getMembersInLine().get(2));
            assertEquals("William", lunchClub.getMembersInLine().get(3));
            assertEquals("Wilma", lunchClub.getMembersInLine().get(4));
            assertEquals("Warren", lunchClub.getMembersInLine().get(5));
        } catch(IndexOutOfBoundsException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void lunchClub_serveMember_returnsFirstPersonAndRemovesThem() {
        String memberServed = lunchClub.serveMember();
        assertEquals("Jane", memberServed);
        assertEquals(2, lunchClub.getMembersInLine().size());
        assertEquals("James", lunchClub.getMembersInLine().getFirst());
    }

    @Test
    public void lunchClub_serveMember_returnsAllMembersInOrder() {
        String memberServed1 = lunchClub.serveMember();
        String memberServed2 = lunchClub.serveMember();
        String memberServed3 = lunchClub.serveMember();
        String memberServed4 = lunchClub.serveMember();
        assertEquals("Jane", memberServed1);
        assertEquals("James", memberServed2);
        assertEquals("John", memberServed3);
        assertEquals("", memberServed4);
        assertEquals(0, lunchClub.getMembersInLine().size());
    }

}
