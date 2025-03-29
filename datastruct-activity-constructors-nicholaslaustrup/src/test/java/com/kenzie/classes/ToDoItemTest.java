package com.kenzie.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ToDoItemTest {
    Object [] item = {"walk the cat", "high"};
    public static Class<?> [] paramTypes = {String.class, String.class};

    @Test
    void canCreateDefaultToDoItem() {
        try {
            Constructor toDoItemConstructor = ToDoItem.class.getConstructor();
            ToDoItem item1 = (ToDoItem) toDoItemConstructor.newInstance();

            Method getDescription = ToDoItem.class.getMethod("getDescription");
            Method getPriority = ToDoItem.class.getMethod("getPriority");
            Method getIsDone = ToDoItem.class.getMethod("getIsDone");

            String value = (String) getDescription.invoke(item1);
            assertEquals("", value, "Constructor default test: description=\"\"");

            value = (String) getPriority.invoke(item1);
            assertEquals("", value, "Constructor default test: priority=\"\"");

            boolean priority = (boolean) getIsDone.invoke(item1);
            assertEquals(false, priority, "Constructor default test: isDone =false");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoItem and all setter/getter methods must be defined");
        }
    }

    @Test
    void canCreateToDoItem() {
        try {
            Constructor toDoItemConstructor = ToDoItem.class.getConstructor(paramTypes);
            ToDoItem item2 = (ToDoItem) toDoItemConstructor.newInstance(item);

            Method getDescription = ToDoItem.class.getMethod("getDescription");
            Method getPriority = ToDoItem.class.getMethod("getPriority");
            Method getIsDone = ToDoItem.class.getMethod("getIsDone");

            String value = (String) getDescription.invoke(item2);
            assertEquals("walk the cat", value, "Constructor value test: description=walk the cat" );

            value = (String) getPriority.invoke(item2);
            assertEquals("high", value, "Constructor value test: priority=high");

            boolean priority = (boolean) getIsDone.invoke(item2);
            assertEquals(false, priority, "Constructor value test: isDone =false");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoItem and all setter/getter methods must be defined");
        }
    }

    @Test
    void setDescription() {
        try {
            Constructor toDoItemConstructor = ToDoItem.class.getConstructor(paramTypes);
            ToDoItem item2 = (ToDoItem) toDoItemConstructor.newInstance(item);
            Method getDescription = ToDoItem.class.getMethod("getDescription");
            Method setDescription = ToDoItem.class.getMethod("setDescription", String.class);

            setDescription.invoke(item2, "Do java homework");
            String value = (String) getDescription.invoke(item2);
            assertEquals("Do java homework", value, "setDescription test: description=Do java homeworkt" );
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoItem and all setter/getter methods must be defined");
        }
    }

    @Test
    void setPriority() {
        try {
            Constructor toDoItemConstructor = ToDoItem.class.getConstructor(paramTypes);
            ToDoItem item2 = (ToDoItem) toDoItemConstructor.newInstance(item);
            Method getPriority = ToDoItem.class.getMethod("getPriority");
            Method setPriority = ToDoItem.class.getMethod("setPriority", String.class);

            setPriority.invoke(item2, "low");
            String value = (String) getPriority.invoke(item2);
            assertEquals("low", value, "setPriority test: priority=low");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("ToDoItem and all setter/getter methods must be defined");
        }


    }

    @Test
    void setIsDone() {
        try {
            Constructor toDoItemConstructor = ToDoItem.class.getConstructor(paramTypes);
            ToDoItem item2 = (ToDoItem) toDoItemConstructor.newInstance(item);
            Method getIsDone = ToDoItem.class.getMethod("getIsDone");
            Method setIsDone = ToDoItem.class.getMethod("setIsDone", boolean.class);

            setIsDone.invoke(item2, true);
            boolean value = (boolean) getIsDone.invoke(item2);
            assertEquals(true, value, "setIsDone: isDone =true");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }



    }
}
