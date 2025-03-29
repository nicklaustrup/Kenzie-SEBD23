package com.kenzie.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // Set up streams to test console input and output
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    public void setTestInput() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void createAccountWithName() {
        Account account = new Account("TestUser");
        assertEquals("TestUser", account.getUserName(), "userName is set correctly") ;
        assertEquals(9, account.getAccountId().length(), "accountId is 9 characters long") ;
        assertEquals(true, account.getAccountId().startsWith("tes"), "accountId starts with tes") ;
        assertEquals(0, account.getBalance(), "balance starts at 0") ;
    }

    @Test
    public void createAccountWithNameAndAccountId() {
        Account account = new Account("TestUser2", "acc213324");
        assertEquals("TestUser2", account.getUserName(), "userName is set correctly") ;
        assertEquals("acc213324", account.getAccountId(), "accountId is set correctly") ;
        assertEquals(0, account.getBalance(), "balance starts at 0") ;
    }

    @Test
    public void createAccountWithNameAccountIdAndBalance() {
        Account account = new Account("TestUser3", "acc213325", 250152.12);
        assertEquals("TestUser3", account.getUserName(), "userName is set correctly") ;
        assertEquals("acc213325", account.getAccountId(), "accountId is set correctly") ;
        assertEquals(250152.12, account.getBalance(), "balance is set correctly") ;
    }

    @Test
    public void canMakeDeposit() {
        Account account = new Account("TestUser");
        assertEquals(0, account.getBalance(), "balance starts at 0") ;
        account.makeDeposit(2000.00);
        assertEquals(2000.00, account.getBalance(), "balance updates") ;
        account.makeDeposit(2123.45);
        assertEquals(4123.45, account.getBalance(), "balance updates second time") ;
    }

    @Test
    public void canMakeSuccessfulWithdrawal() {
        Account account = new Account("TestUser", "tes123456", 5000);
        assertEquals(5000.00, account.getBalance(), "balance initializes") ;
        double returnAmount = account.makeWithdrawal(1000.00);
        assertEquals(1000.0, returnAmount, "Correct amount is returned");
        assertEquals(4000.0, account.getBalance(), "balance updates") ;
        returnAmount = account.makeWithdrawal(1000.00);
        assertEquals(1000.0, returnAmount, "Correct amount is returned second time");
        assertEquals(3000.0, account.getBalance(), "balance updates second time") ;
    }

    @Test
    public void canMakeFailingWithdrawal() {
        Account account = new Account("TestUser", "tes123456", 5000);
        assertEquals(5000.00, account.getBalance(), "balance initializes") ;
        double returnAmount = account.makeWithdrawal(8000.00);
        assertEquals(0, returnAmount, "0 is returned");
        assertEquals(5000.0, account.getBalance(), "balance does not change updates") ;
    }

    @Test
    public void canMakeSuccessfulTransfer() {
        Account account1 = new Account("TestUser1", "tes123456", 5000);
        Account account2 = new Account("TestUser2", "tes654321", 0);
        assertEquals(5000.00, account1.getBalance(), "account1 balance starts at 5000");
        assertEquals(0.0, account2.getBalance(), "account2 balance starts at 0");
        Main.transferBetween(account1, account2, 3000.0);
        assertEquals(2000.00, account1.getBalance(), "account1 balance updates");
        assertEquals(3000.0, account2.getBalance(), "account2 balance updates");

        //assertEquals("Payment completed. Transferred 3000.0 from tes123456 to tes654321\n", outContent.toString(), "Correct message is printed");
        //Modify unit test to do string contains check
        assertEquals(true, outContent.toString().indexOf("Payment completed. Transferred 3000.0 from tes123456 to tes654321") > -1,"Expected: 'Payment completed. Transferred 3000.0 from tes123456 to tes654321'. Actual: " + outContent.toString() + "\nCheck for exact match including spaces.");
    }

    @Test
    public void canMakeFailedTransfer() {
        Account account1 = new Account("TestUser1", "tes123456", 5000);
        Account account2 = new Account("TestUser2", "tes654321", 0);
        assertEquals(5000.00, account1.getBalance(), "account1 balance starts at 5000");
        assertEquals(0.0, account2.getBalance(), "account2 balance starts at 0");
        Main.transferBetween(account1, account2, 8000.0);
        assertEquals(5000.00, account1.getBalance(), "account1 balance does not update");
        assertEquals(0.0, account2.getBalance(), "account2 balance does not update");

        //assertEquals("Payment failed. Insufficient Funds.\n", outContent.toString(), "Correct message is printed");
        //Modify unit test to do string contains check
        assertEquals(true, outContent.toString().indexOf("Payment failed. Insufficient Funds.") > -1,"Expected:'Payment failed. Insufficient Funds.' Actual: " + outContent.toString() + "\nCheck for exact match including spaces.");
    }


    @Test
    public void runMain() {
        boolean crashed = false;
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            crashed = true;
        }

        assertEquals(false, crashed, "App can run without crashing") ;
    }
}

