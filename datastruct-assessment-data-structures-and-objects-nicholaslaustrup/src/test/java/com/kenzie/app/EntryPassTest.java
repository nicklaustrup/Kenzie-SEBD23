package com.kenzie.app;

import org.junit.jupiter.api.Test;
import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

public class EntryPassTest {

    Object [] homerVip = {"Homer", "12345", "VIP"};
    Object [] homerStandard = {"Homer", "34567", "standard"};
    Class<?> [] paramTypes = {String.class, String.class, String.class};


    @Test
    public void testEntryPassEqualityReflexivity() {
        try {
            Constructor entryPassConstructor = EntryPass.class.getConstructor(paramTypes);
            EntryPass e1 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            assertTrue(e1.equals(e1));
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("EntryPass and all setter/getter methods must be defined");
        }
    }

    @Test
    public void testEntryPassEqualitySymmetry(){
        try {
            Constructor<EntryPass> entryPassConstructor = EntryPass.class.getConstructor(paramTypes);
            EntryPass e1 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            EntryPass e2 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            assertTrue(e1.equals(e2));
            assertTrue(e2.equals(e1));
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("EntryPass and all setter/getter methods must be defined");
        }

    }

    @Test
    public void testEntryPassEqualityTransitivity(){
        try {
            Constructor<EntryPass> entryPassConstructor = EntryPass.class.getConstructor(paramTypes);
            EntryPass e1 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            EntryPass e2 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            EntryPass e3 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            assertTrue(e1.equals(e2));
            assertTrue(e2.equals(e3));
            assertTrue(e1.equals(e3));
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("EntryPass and all setter/getter methods must be defined");
        }
    }

    @Test
    public void testEntryPassEqualityConsistency(){
        try{
            Constructor<EntryPass> entryPassConstructor = EntryPass.class.getConstructor(paramTypes);
            EntryPass e1 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            EntryPass e2 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            assertTrue(e1.equals(e2));
            assertTrue(e1.equals(e2));

            e1 = (EntryPass) entryPassConstructor.newInstance(homerStandard);
            e2 = (EntryPass) entryPassConstructor.newInstance(homerStandard);
            assertTrue(e1.equals(e2));
            assertTrue(e1.equals(e2));
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("EntryPass and all setter/getter methods must be defined");
        }
    }

    @Test
    public void testEntryPassEqualityNonNullity(){
        try{
            Constructor<EntryPass> entryPassConstructor = EntryPass.class.getConstructor(paramTypes);
            EntryPass e1 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            assertFalse(e1.equals(null));
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("EntryPass and all setter/getter methods must be defined");
        }
    }

    @Test
    public void testEntryPassEqualityNotEqual(){
        try {
            Constructor<EntryPass> entryPassConstructor = EntryPass.class.getConstructor(paramTypes);
            EntryPass e1 = (EntryPass) entryPassConstructor.newInstance(homerVip);
            EntryPass e2 = (EntryPass) entryPassConstructor.newInstance(homerStandard);
            assertFalse(e1.equals(e2));
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("EntryPass and all setter/getter methods must be defined");
        }
    }


}

