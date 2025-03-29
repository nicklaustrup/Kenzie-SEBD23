package ata.unit.three.project.expense.service;

import ata.unit.three.project.expense.dynamodb.ExpenseItem;
import ata.unit.three.project.expense.dynamodb.ExpenseItemList;
import ata.unit.three.project.expense.dynamodb.ExpenseServiceRepository;
import ata.unit.three.project.expense.lambda.models.Expense;
import ata.unit.three.project.expense.service.exceptions.InvalidDataException;
import ata.unit.three.project.expense.service.exceptions.InvalidExpenseException;
import ata.unit.three.project.expense.service.exceptions.ItemNotFoundException;
import ata.unit.three.project.expense.service.model.ExpenseItemConverter;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;

public class ExpenseService {

    private ExpenseServiceRepository expenseServiceRepository;
    private ExpenseItemConverter expenseItemConverter;

    @Inject
    public ExpenseService(ExpenseServiceRepository expenseServiceRepository,
                          ExpenseItemConverter expenseItemConverter) {
        this.expenseServiceRepository = expenseServiceRepository;
        this.expenseItemConverter = expenseItemConverter;
    }

    public ExpenseItem getExpenseById(String expenseId) {
        if (StringUtils.isEmpty(expenseId) || isInvalidUuid(expenseId)) {
            throw new InvalidDataException("Expense id is not present");
        }
        return expenseServiceRepository.getExpenseById(expenseId);
    }

    public List<ExpenseItem> getExpensesByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new InvalidDataException("Email is not present");
        }
        return expenseServiceRepository.getExpensesByEmail(email);
    }

    public String createExpense(Expense expense) {
        ExpenseItem expenseItem = expenseItemConverter.convert(expense);
        expenseServiceRepository.createExpense(expenseItem);
        return expenseItem.getId();
    }

    public void updateExpense(String expenseId, Expense updateExpense) {
        if (StringUtils.isEmpty(expenseId) || isInvalidUuid(expenseId)) {
            throw new InvalidDataException("Expense id is not present");
        }
        ExpenseItem item = expenseServiceRepository.getExpenseById(expenseId);
        if (item == null) {
            throw new ItemNotFoundException("Expense does not exist");
        }
        expenseServiceRepository.updateExpense(expenseId,
                updateExpense.getTitle(),
                updateExpense.getAmount());
    }

    public void deleteExpense(String expenseId) {
        if (StringUtils.isEmpty(expenseId) || isInvalidUuid(expenseId)) {
            throw new InvalidDataException("Expense id is not present");
        }
        expenseServiceRepository.deleteExpense(expenseId);
    }

    public String createExpenseList(String email, String title) {
        String expenseListId = randomUUID().toString();
        expenseServiceRepository.createExpenseList(expenseListId, email, title);
        return expenseListId;
    }

    public void addExpenseItemToList(String id, String expenseId) {
        if (id.isEmpty() || isInvalidUuid(id)){
            throw new InvalidDataException("Expense list ID must be a valid UUID");
        }
        if (expenseId.isEmpty() || isInvalidUuid(expenseId)){
            throw new InvalidDataException("Expense list ID must be a valid UUID");
        }

        //Code inspired from Nivi during Q&A

        ExpenseItem item = expenseServiceRepository.getExpenseById(expenseId);

        if (item == null){
            throw new ItemNotFoundException("Expense item is null");
        }

        ExpenseItemList expenseItemList = expenseServiceRepository.getExpenseListById(id);

        if (expenseItemList == null){
            throw new ItemNotFoundException("Must use a valid expense list ID");
        }

        if (!(expenseItemList.getEmail().equals(item.getEmail()))){
            throw new InvalidDataException("Emails must match");
        }

        List<ExpenseItem> listOfExpenseItem = expenseItemList.getExpenseItems();

        if (listOfExpenseItem != null && listOfExpenseItem.contains(item)){
            throw new InvalidDataException("Item is already in the list");
        }


        expenseServiceRepository.addExpenseItemToList(id, item);

    }

    public void removeExpenseItemFromList(String id, String expenseId) {
        if (id.isEmpty() || isInvalidUuid(id)){
            throw new InvalidDataException("ID is not valid");
        }
        if (expenseId.isEmpty() || isInvalidUuid(expenseId)) {
            throw new InvalidDataException("Expense id is not valid");
        }
        ExpenseItem item = expenseServiceRepository.getExpenseById(expenseId);
        if (item == null){
            throw new ItemNotFoundException("Expense item is null");
        }
        ExpenseItemList expenseItemList = expenseServiceRepository.getExpenseListById(id);
        if (expenseItemList == null){
            throw new ItemNotFoundException("Must use a valid expense list ID");
        }
        if (!item.getEmail().equals(expenseItemList.getEmail())){
            throw new InvalidDataException("Emails must match");
        }
        // Inspired by Eduardo's code
        if (expenseItemList.getExpenseItems() != null){
            for (ExpenseItem expense: expenseItemList.getExpenseItems()) {
                if (expense.getId().equals(item.getId())){
                    expenseServiceRepository.removeExpenseItemToList(id, expense);
                    return;
                }
            }
            throw new InvalidExpenseException("Expense is not part of the list");
        }
    }

    public List<ExpenseItemList> getExpenseListByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new InvalidDataException("Email is not present");
        }

        List<ExpenseItemList> list = expenseServiceRepository.getExpenseListsByEmail(email);

        for (ExpenseItemList itemList : list) {
            List<ExpenseItem> listOfExpenseItems = itemList.getExpenseItems();
            //add expense items back into expenseItemList
//
//            expenseItemList.setExpenseItems(listOfExpenseItems);
//            expenseItemList.setEmail(itemList.getEmail());
//            expenseItemList.setId(itemList.getId());
//            expenseItemList.setTitle(itemList.getTitle());
            Comparator<ExpenseItem> comparator = Comparator.comparing( ExpenseItem::getExpenseDate );
            listOfExpenseItems.sort(comparator);

//            sortedList.add(expenseItemList);
        }
        return list;
    }

    private boolean isInvalidUuid(String uuid) {
        try {
            fromString(uuid);
        } catch (IllegalArgumentException exception) {
            return true;
        }
        return false;
    }
}
