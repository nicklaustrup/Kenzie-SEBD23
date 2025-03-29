package ata.unit.three.project.expense.service;

import ata.unit.three.project.expense.dynamodb.ExpenseItem;
import ata.unit.three.project.expense.dynamodb.ExpenseItemList;
import ata.unit.three.project.expense.dynamodb.ExpenseServiceRepository;
import ata.unit.three.project.expense.lambda.models.Expense;
import ata.unit.three.project.expense.service.exceptions.InvalidDataException;
import ata.unit.three.project.expense.service.exceptions.InvalidExpenseException;
import ata.unit.three.project.expense.service.exceptions.ItemNotFoundException;
import ata.unit.three.project.expense.service.model.ExpenseItemConverter;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    private final MockNeat mockNeat = MockNeat.threadLocal();

    /** ------------------------------------------------------------------------
     *  expenseService.getExpenseById
     *  ------------------------------------------------------------------------ **/


    @Test
    void getExpenseById_returnsExpense() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        expenseItem.setId(id);
        expenseItem.setEmail(mockNeat.emails().val());
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        //WHEN
        when(expenseServiceRepository.getExpenseById(id)).thenReturn(expenseItem);

        //THEN
        ExpenseItem returnedExpenseItem = expenseService.getExpenseById(id);
        Assertions.assertEquals(returnedExpenseItem.getId(), expenseItem.getId());
        Assertions.assertEquals(returnedExpenseItem.getEmail(), expenseItem.getEmail());
        Assertions.assertEquals(returnedExpenseItem.getTitle(), expenseItem.getTitle());
        Assertions.assertEquals(returnedExpenseItem.getExpenseDate(), expenseItem.getExpenseDate());
    }
    @Test
    void getExpensesById_nullID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = null;
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());


        //WHEN
        //THEN
        assertThrows(InvalidDataException.class,
                ()-> expenseService.getExpenseById(id));
    }
    @Test
    void getExpensesById_emptyExpenseID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = "";
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());


        //WHEN
        //THEN
        assertThrows(InvalidDataException.class,
                ()-> expenseService.getExpenseById(id));
    }

    // Write additional tests here

    /** ------------------------------------------------------------------------
     *  expenseService.getExpensesByEmail
     *  ------------------------------------------------------------------------ **/

    @Test
    void getExpensesByEmail_returnsExpense() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String email = mockNeat.emails().val();
        expenseItem.setId(id);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        List<ExpenseItem> expenseItemList = Collections.singletonList(expenseItem);

        //WHEN
        when(expenseServiceRepository.getExpensesByEmail(email)).thenReturn(expenseItemList);

        //THEN
        List<ExpenseItem> returnedExpenseList = expenseService.getExpensesByEmail(email);
        assertEquals(returnedExpenseList.size(), 1);
        assertEquals(returnedExpenseList.get(0).getId(), id);
        assertEquals(returnedExpenseList.get(0).getEmail(), email);
    }

    @Test
    void getExpensesByEmail_doesNotExist_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String email = "";

        //WHEN
        //THEN
      assertThrows(InvalidDataException.class,
              ()-> expenseService.getExpensesByEmail(email));

    }

    // Write additional tests here

    /** ------------------------------------------------------------------------
     *  expenseService.updateExpense
     *  ------------------------------------------------------------------------ **/
    @Test
    void updateExpense_expensesUpdated() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);
        Expense expense = mock(Expense.class);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        expenseItem.setId(id);
        expenseItem.setEmail(mockNeat.emails().val());
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());


        //WHEN
        when(expenseService.getExpenseById(id)).thenReturn(expenseItem);
        expenseService.updateExpense(id, expense);


        //THEN
        ExpenseItem returnedExpenseItem = expenseService.getExpenseById(id);
        Assertions.assertEquals(returnedExpenseItem.getId(), expenseItem.getId());
        Assertions.assertEquals(returnedExpenseItem.getEmail(), expenseItem.getEmail());
        Assertions.assertEquals(returnedExpenseItem.getTitle(), expenseItem.getTitle());
        Assertions.assertEquals(returnedExpenseItem.getExpenseDate(), expenseItem.getExpenseDate());
    }

    @Test
    void updateExpense_invalidUUID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);
        Expense expense = mock(Expense.class);

        String id = "";

        //WHEN
        //THEN
        assertThrows(InvalidDataException.class,
                ()-> expenseService.updateExpense(id, expense));
    }
    @Test
    void updateExpense_itemNotFound_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);
        Expense expense = mock(Expense.class);


        String id = UUID.randomUUID().toString();


        //WHEN
        when(expenseServiceRepository.getExpenseById(id)).thenReturn(null);

        //THEN
        assertThrows(ItemNotFoundException.class,
                () -> expenseService.updateExpense(id, expense));
    }

    // Write additional tests here

    /** ------------------------------------------------------------------------
     *  expenseService.deleteExpense
     *  ------------------------------------------------------------------------ **/
    @Test
    void deleteExpense_expenseDeleted() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        expenseItem.setId(id);
        expenseItem.setEmail(mockNeat.emails().val());
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());


        //WHEN
        expenseService.deleteExpense(id);

        //THEN
        Assertions.assertNull(expenseService.getExpenseById(id));
    }
    @Test
    void deleteExpense_invalidUUID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = "";
        expenseItem.setId(id);
        expenseItem.setEmail(mockNeat.emails().val());
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());


        //WHEN
        //THEN
        assertThrows(InvalidDataException.class,
                ()-> expenseService.deleteExpense(id));

    }


    // Write additional tests here

    /** ------------------------------------------------------------------------
     *  expenseService.addExpenseItemToList
     *  ------------------------------------------------------------------------ **/

    @Test
    void add_expense_item_to_list() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String email = mockNeat.emails().val();
        expenseItem.setId(id);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());


        doNothing().when(expenseServiceRepository).addExpenseItemToList(id, expenseItem);


        List<ExpenseItem> expenseItemList = Collections.singletonList(expenseItem);
        expenseServiceRepository.addExpenseItemToList(id, expenseItem);

        //WHEN
        when(expenseServiceRepository.getExpensesByEmail(email)).thenReturn(expenseItemList);

        //THEN
        List<ExpenseItem> returnedExpenseList = expenseService.getExpensesByEmail(email);
        assertEquals(returnedExpenseList.size(), 1);
        assertEquals(returnedExpenseList.get(0).getId(), id);
        assertEquals(returnedExpenseList.get(0).getEmail(), email);
    }

    @Test
    void addExpenseItemToList_invalidID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = "UUID.randomUUID().toString()";
        String expenseId = UUID.randomUUID().toString();

        assertThrows(InvalidDataException.class,
                ()-> expenseService.addExpenseItemToList(id, expenseId));
    }

    @Test
    void addExpenseItemToList_invalidExpenseID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = UUID.randomUUID().toString();
        String expenseId = "UUID.randomUUID().toString()";

        assertThrows(InvalidDataException.class,
                ()-> expenseService.addExpenseItemToList(id, expenseId));
    }

    @Test
    void addExpenseItemToList_expenseItemNull_throwsItemNotFoundException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();

        assertThrows(
                ItemNotFoundException.class,
                ()-> expenseService.addExpenseItemToList(id, expenseId));
    }
    @Test
    void addExpenseItemToList_expenseItemListNull_throwsItemNotFoundException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();

        ExpenseItemList expenseItemList = new ExpenseItemList();

        when(expenseServiceRepository.getExpenseListById(id)).thenReturn(expenseItemList);


        //WHEN
        assertThrows(
                ItemNotFoundException.class,
                ()-> expenseService.addExpenseItemToList(id, expenseId));
    }
    @Test
    void addExpenseItemToList_differentEmails_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();
        String email = "bezos_is_awesome@gmail.com";
        expenseItem.setId(expenseId);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        ExpenseItemList expenseItemList = new ExpenseItemList();
        expenseItemList.setId(id);
        expenseItemList.setEmail("bayzosuxx@gmail.com");

        when(expenseServiceRepository.getExpenseById(expenseId)).thenReturn(expenseItem);
        when(expenseServiceRepository.getExpenseListById(id)).thenReturn(expenseItemList);


        //WHEN
        assertThrows(
                InvalidDataException.class,
                ()-> expenseService.addExpenseItemToList(id, expenseId));
    }
    @Test
    void addExpenseItemToList_expenseItemAlreadyExists_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();
        String email = "jeffbezos@google.com";
        expenseItem.setId(expenseId);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        ExpenseItemList expenseItemList = new ExpenseItemList();
        expenseItemList.setId(id);
        expenseItemList.setEmail("jeffbezos@google.com");

        List<ExpenseItem> listOfExpenseItem = Collections.singletonList(expenseItem);
        expenseItemList.setExpenseItems(listOfExpenseItem);

        when(expenseServiceRepository.getExpenseById(expenseId)).thenReturn(expenseItem);
        when(expenseServiceRepository.getExpenseListById(id)).thenReturn(expenseItemList);


        //WHEN
        assertThrows(
                InvalidDataException.class,
                ()-> expenseService.addExpenseItemToList(id, expenseId));
    }
    // Write additional tests here

    /** ------------------------------------------------------------------------
     *  expenseService.removeExpenseItemFromList
     *  ------------------------------------------------------------------------ **/

    @Test
    void removeExpenseItem() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String email = mockNeat.emails().val();
        expenseItem.setId(id);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        expenseServiceRepository.addExpenseItemToList(id, expenseItem);

        //WHEN
        expenseServiceRepository.removeExpenseItemToList(id, expenseItem);

        //THEN
        List<ExpenseItem> returnedExpenseList = expenseService.getExpensesByEmail(email);
        assertEquals(returnedExpenseList.size(), 0);
    }
    @Test
    void removeExpenseItemToList_invalidID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = "UUID.randomUUID().toString()";
        String expenseId = UUID.randomUUID().toString();
        //id is invalid uuid
        assertThrows(InvalidDataException.class,
                ()-> expenseService.removeExpenseItemFromList(id, expenseId));
        //id is empty
        String nullId = "";
        assertThrows(InvalidDataException.class,
                ()-> expenseService.removeExpenseItemFromList(nullId, expenseId));
    }
    @Test
    void removeExpenseItemToList_invalidExpenseID_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = UUID.randomUUID().toString();
        String expenseId = "UUID.randomUUID().toString()";

        //Expense ID is invalid
        assertThrows(InvalidDataException.class,
                ()-> expenseService.removeExpenseItemFromList(id, expenseId));

        //Expense ID is empty
        String emptyId = "";
        assertThrows(InvalidDataException.class,
                ()-> expenseService.removeExpenseItemFromList(id, emptyId));
    }
    @Test
    void removeExpenseItemToList_expenseItemNull_throwsItemNotFoundException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();

        assertThrows(
                ItemNotFoundException.class,
                ()-> expenseService.removeExpenseItemFromList(id, expenseId));
    }
    @Test
    void removeExpenseItemToList_expenseItemListNull_throwsItemNotFoundException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();

        ExpenseItemList expenseItemList = new ExpenseItemList();

        when(expenseServiceRepository.getExpenseListById(id)).thenReturn(expenseItemList);


        //WHEN
        assertThrows(
                ItemNotFoundException.class,
                ()-> expenseService.removeExpenseItemFromList(id, expenseId));
    }
    @Test
    void removeExpenseItemToList_differentEmails_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();
        String email = "bezos_is_awesome@gmail.com";
        expenseItem.setId(expenseId);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        ExpenseItemList expenseItemList = new ExpenseItemList();
        expenseItemList.setId(id);
        expenseItemList.setEmail("bayzosuxx@gmail.com");

        when(expenseServiceRepository.getExpenseById(expenseId)).thenReturn(expenseItem);
        when(expenseServiceRepository.getExpenseListById(id)).thenReturn(expenseItemList);


        //WHEN
        assertThrows(
                InvalidDataException.class,
                ()-> expenseService.removeExpenseItemFromList(id, expenseId));
    }
    @Test
    void removeExpenseItemToList_expenseItemDoesntExist_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String expenseId = UUID.randomUUID().toString();
        String email = "jeffbezos@google.com";
        expenseItem.setId(expenseId);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        ExpenseItemList expenseItemList = new ExpenseItemList();
        expenseItemList.setId(id);
        expenseItemList.setEmail("jeffbezos@google.com");

        List<ExpenseItem> listOfExpenseItem = new ArrayList<>();
        expenseItemList.setExpenseItems(listOfExpenseItem);

        when(expenseServiceRepository.getExpenseById(expenseId)).thenReturn(expenseItem);
        when(expenseServiceRepository.getExpenseListById(id)).thenReturn(expenseItemList);


        //WHEN
        assertThrows(
                InvalidExpenseException.class,
                ()-> expenseService.removeExpenseItemFromList(id, expenseId));
    }


    /** ------------------------------------------------------------------------
     *  expenseService.getExpenseListByEmail
     *  ------------------------------------------------------------------------ **/

    @Test
    void get_expense_list_by_email() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        ExpenseItem expenseItem = new ExpenseItem();
        String id = UUID.randomUUID().toString();
        String email = mockNeat.emails().val();
        expenseItem.setId(id);
        expenseItem.setEmail(email);
        expenseItem.setExpenseDate(Instant.now().toString());
        expenseItem.setTitle(mockNeat.strings().val());

        //WHEN
        ExpenseItemList expenseItemList = new ExpenseItemList();
        String expenseListId = mockNeat.strings().val();
        expenseItemList.setEmail(email);
        expenseItemList.setTitle(mockNeat.strings().val());
        expenseItemList.setId(expenseListId);
        expenseItemList.setExpenseItems(Collections.singletonList(expenseItem));
        List<ExpenseItemList> list = Collections.singletonList(expenseItemList);

        when(expenseServiceRepository.getExpenseListsByEmail(anyString())).thenReturn(list);

        //THEN
        List<ExpenseItemList> returnedExpenseList = expenseService.getExpenseListByEmail(email);
        assertEquals(returnedExpenseList.size(), 1);
        assertEquals(returnedExpenseList.get(0).getId(), expenseListId);
        assertEquals(returnedExpenseList.get(0).getEmail(), email);
    }
    @Test
    void getExpenseListByEmail_emptyEmail_throwsInvalidDataException() {
        //GIVEN
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String email = "";

        //THEN
        assertThrows(InvalidDataException.class,
                ()-> expenseService.getExpenseListByEmail(email));
    }

/** ------------------------------------------------------------------------
 *  expenseService.createExpenseList
 *  ------------------------------------------------------------------------ **/

    @Test
    void createExpenseList(){
        ExpenseServiceRepository expenseServiceRepository = mock(ExpenseServiceRepository.class);
        ExpenseItemConverter expenseItemConverter = mock(ExpenseItemConverter.class);
        ExpenseService expenseService = new ExpenseService(expenseServiceRepository, expenseItemConverter);

        String email = mockNeat.emails().val();
        String title = "Expense List Title";

        String expected = expenseService.createExpenseList(email, title);

        verify(expenseServiceRepository).createExpenseList(expected, email, title);
    }
}