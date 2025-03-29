package com.kenzie.app;

import java.util.*;

public class Main {

    /*
        A EntryPass is unique if there is no pass with the same ID and access level.
        You will need to override equals() in EntryPass to check for equality of these items
        The equals() comparison is case-insensitive meaning VIP is equal to vip. 
     */
    public static boolean addPassIfUnique(HashMap<String, EntryPass> entryPassHashMap, EntryPass entryPass) {
        //fill in method

        for (Map.Entry<String, EntryPass> unique : entryPassHashMap.entrySet()) {
            if (unique.getValue().equals(entryPass)) {
                return false;
            }
        }
        entryPassHashMap.put(entryPass.getName() + entryPass.getID(), entryPass);
        return true;


    }


    /*
        getVIPList should return any name with a VIP pass. In this method, consider any case value of "vip"
        to be a valid VIP pass. Example: "vip" and "VIP" are both VIP passes.
     */
    public static ArrayList<String> getVIPList(HashMap<String, EntryPass> hashMap) {
        //fill in method

        ArrayList<String> vipList = new ArrayList<>();

        for (Map.Entry<String, EntryPass> vipLevel : hashMap.entrySet()) {
            if (vipLevel.getValue().getAccessLevel().equalsIgnoreCase("VIP")) {
                vipList.add(vipLevel.getValue().getName());
            }
        }
        return vipList;
    }


    public static void main(String[] args) {
        HashMap<String, EntryPass> entryPassesByName = new HashMap<>();
        ArrayList<String> blockList = new ArrayList<>();

        //define local variables as needed
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;


        //Entry Pass key tokens
        String name;
        String id;
        String accessLevel;


        //Entry Pass scanner
        while (!exitLoop) {


            // User input scanners
            System.out.println("Write your name: ");
            name = scanner.nextLine();
            System.out.println("Write your ID: ");
            id = scanner.nextLine();
            System.out.println("Write your Access Level: ");
            accessLevel = scanner.nextLine();

            //Entry Pass class using inputs
            EntryPass entryPass = new EntryPass(name, id, accessLevel);

            //Blocked list portion of Unique Check
            if (!addPassIfUnique(entryPassesByName, entryPass)) {
                blockList.add(entryPass.getName());
                System.out.println("Blocked");
            }


            // Exit loop sequence
            System.out.println("Continue? Y/N");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("N")) {
                exitLoop = true;
            }
        }


        //Use this print statement at the end of the program. Do not modify!
        System.out.println("Entry Pass List:");
        System.out.println(entryPassesByName.entrySet());
        System.out.println("Blocked list:");
        System.out.println(blockList);
        System.out.println("VIP List:");
        System.out.println(getVIPList(entryPassesByName));


    }
}

