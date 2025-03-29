package com.kenzie.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    Object [] two = {"Richie Rich"};
    Object [] three = {2,"Scrooge", 1000.99};
    public static Class<?> [] paramTypesTwo = {String.class};
    public static Class<?> [] paramTypesThree = {int.class, String.class, double.class};


    @Test
    void canCreateDefaultWallet() {
        try{
            Constructor walletConstructor = Wallet.class.getConstructor();
            Wallet wallet = (Wallet) walletConstructor.newInstance();
            Method getOwner = Wallet.class.getMethod("getOwner");
            Method getNumCreditCards = Wallet.class.getMethod("getNumCreditCards");
            Method getTotalCash = Wallet.class.getMethod("getTotalCash");

            assertEquals("", getOwner.invoke(wallet), "Constructor default test: owner=\"\"" );
            assertEquals(0, getNumCreditCards.invoke(wallet), "Constructor default test: numCreditCards=0");
            assertEquals(0.0, getTotalCash.invoke(wallet), "Constructor default test: totalCash = 0");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Wallet and all setter/getter methods must be defined");
        }
    }

    @Test
    void canCreateWallet() {
        try{
            Constructor walletConstructorTwo = Wallet.class.getConstructor(paramTypesTwo);
            Wallet wallet2 = (Wallet) walletConstructorTwo.newInstance(two);

            Constructor walletConstructorThree = Wallet.class.getConstructor(paramTypesThree);
            Wallet wallet3 = (Wallet) walletConstructorThree.newInstance(three);


            Method getOwner = Wallet.class.getMethod("getOwner");
            Method getNumCreditCards = Wallet.class.getMethod("getNumCreditCards");
            Method getTotalCash = Wallet.class.getMethod("getTotalCash");

            assertEquals("Richie Rich", getOwner.invoke(wallet2), "Constructor default test: owner=Richie Rich" );
            assertEquals(0, getNumCreditCards.invoke(wallet2), "Constructor default test: numCreditCards=0");
            assertEquals(0.0, getTotalCash.invoke(wallet2), "Constructor default test: totalCash = 0");

            assertEquals("Scrooge", getOwner.invoke(wallet3), "Constructor default test: owner=Richie Rich" );
            assertEquals(2, getNumCreditCards.invoke(wallet3), "Constructor default test: numCreditCards=0");
            assertEquals(1000.99, getTotalCash.invoke(wallet3), "Constructor default test: totalCash = 0");

        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Wallet and all setter/getter methods must be defined");
        }
    }



    @Test
    void canSetNumCreditCards() {
        try {
            Constructor walletConstructor = Wallet.class.getConstructor();
            Method getNumCreditCards = Wallet.class.getMethod("getNumCreditCards");
            Method setNumCreditCards = Wallet.class.getMethod("setNumCreditCards", int.class);

            Wallet wallet = (Wallet) walletConstructor.newInstance();
            setNumCreditCards.invoke(wallet, 10);
            assertEquals(10, getNumCreditCards.invoke(wallet), "setNumCreditCards test: numCreditCards=10");
        }  catch (Exception e) {
            System.out.println(e.toString());
            fail("Wallet and all setter/getter methods must be defined");
        }
    }


    @Test
    void canSetOwner() {
        try {
            Constructor walletConstructor = Wallet.class.getConstructor();
            Method getOwner = Wallet.class.getMethod("getOwner");
            Method setOwner = Wallet.class.getMethod("setOwner", String.class);

            Wallet wallet = (Wallet) walletConstructor.newInstance();
            setOwner.invoke(wallet, "Minnie Mouse");
            assertEquals("Minnie Mouse", getOwner.invoke(wallet), "setNumCreditCards test: numCreditCards=10");
        }  catch (Exception e) {
            System.out.println(e.toString());
            fail("Wallet and all setter/getter methods must be defined");
        }
    }


    @Test
    void canSetTotalCash() {
        try {
            Constructor walletConstructor = Wallet.class.getConstructor();
            Method getTotalCash = Wallet.class.getMethod("getTotalCash");
            Method setTotalCash = Wallet.class.getMethod("setTotalCash", double.class);

            Wallet wallet = (Wallet) walletConstructor.newInstance();
            setTotalCash.invoke(wallet, 3);
            assertEquals(3.0, getTotalCash.invoke(wallet), "setNumCreditCards test: numCreditCards=10");
            setTotalCash.invoke(wallet, .99);
            assertEquals(.99, getTotalCash.invoke(wallet), "setNumCreditCards test: numCreditCards=10");
        }  catch (Exception e) {
            System.out.println(e.toString());
            fail("Wallet and all setter/getter methods must be defined");
        }
    }
}