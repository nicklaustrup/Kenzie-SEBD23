package com.kenzie.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    Object[] toDoListObject = {"personal errands", 9};
    public static Class<?> [] paramTypes = {String.class, int.class};
    public static Class<?> [] paramTypesToDoItem = {String.class, String.class};

    @Test
    void canCreateDefaultToDoItem() {
        try {
            Constructor toDoListConstructor = ToDoList.class.getConstructor();
            ToDoList toDoList = (ToDoList) toDoListConstructor.newInstance();
            Method getListName = ToDoList.class.getMethod("getListName");
            Method getMaxItems = ToDoList.class.getMethod("getMaxItems");
            Method getItemList = ToDoList.class.getMethod("getItemList");

            String listName = (String) getListName.invoke(toDoList);
            int maxItems = (int) getMaxItems.invoke(toDoList);
            ArrayList itemList = (ArrayList) getItemList.invoke(toDoList);

            assertEquals("", listName, "Constructor default test: listName=\"\"" );
            assertEquals(10, maxItems, "Constructor default test: maxItems=10");
            assertNotNull(itemList, "Constructor default test: ItemList not null");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoList and all setter/getter methods must be defined");
        }

    }

    @Test
    void canCreateToDoItem() {
        try {
            Constructor toDoListConstructor = ToDoList.class.getConstructor(paramTypes);
            ToDoList toDoList = (ToDoList) toDoListConstructor.newInstance(toDoListObject);
            Method getListName = ToDoList.class.getMethod("getListName");
            Method getMaxItems = ToDoList.class.getMethod("getMaxItems");
            Method getItemList = ToDoList.class.getMethod("getItemList");

            String listName = (String) getListName.invoke(toDoList);
            int maxItems = (int) getMaxItems.invoke(toDoList);
            ArrayList itemList = (ArrayList) getItemList.invoke(toDoList);

            assertEquals("personal errands", listName, "Constructor default test: listName=\"\"" );
            assertEquals(9, maxItems, "Constructor default test: maxItems=9");
            assertNotNull(itemList, "Constructor default test: ItemList not null");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoList and all setter/getter methods must be defined");
        }
    }


    @Test
    void setListName() {
        try {
            Constructor toDoListConstructor = ToDoList.class.getConstructor();
            ToDoList toDoList = (ToDoList) toDoListConstructor.newInstance();
            Method getListName = ToDoList.class.getMethod("getListName");
            Method setListName = ToDoList.class.getMethod("setListName", String.class);
            setListName.invoke(toDoList, "weekend checklist");
            String listName = (String) getListName.invoke(toDoList);
            assertEquals("weekend checklist", listName, "setListName test: listName=weekend checklist");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoList and all setter/getter methods must be defined");
        }
    }


    @Test
    void setMaxItems() {
        try {
            Constructor toDoListConstructor = ToDoList.class.getConstructor();
            ToDoList toDoList = (ToDoList) toDoListConstructor.newInstance();
            Method getMaxItems = ToDoList.class.getMethod("getMaxItems");
            Method setMaxItems = ToDoList.class.getMethod("setMaxItems", int.class);
            setMaxItems.invoke(toDoList, 100);
            int value = (int) getMaxItems.invoke(toDoList);
            assertEquals(100, value, "setMaxItems test: maxItems=100" );
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoList and all setter/getter methods must be defined");
        }
    }


    @Test
    void canSetItemList() {
        try {
            Object[] one = {"buy cake", "high"};
            Object[] two = {"do decorations", "low"};
            Object[] three = {"send invitations", "medium"};
            Constructor toDoItemConstructor = ToDoItem.class.getConstructor(paramTypesToDoItem);
            ToDoItem item1 = (ToDoItem) toDoItemConstructor.newInstance(one);
            ToDoItem item2 = (ToDoItem) toDoItemConstructor.newInstance(two);
            ToDoItem item3 = (ToDoItem) toDoItemConstructor.newInstance(three);

            ArrayList<ToDoItem> partyList = new ArrayList<>();
            partyList.add(item1);
            partyList.add(item2);
            partyList.add(item3);

            Constructor toDoListConstructor = ToDoList.class.getConstructor();
            ToDoList toDoList = (ToDoList) toDoListConstructor.newInstance();
            Method setItemList = ToDoList.class.getMethod("setItemList", ArrayList.class);
            Method getItemList = ToDoList.class.getMethod("getItemList");
            setItemList.invoke(toDoList, partyList);
            ArrayList actualList = (ArrayList) getItemList.invoke(toDoList);
            assertEquals(partyList, actualList, "setItemList test: size=3");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }
     }
}
