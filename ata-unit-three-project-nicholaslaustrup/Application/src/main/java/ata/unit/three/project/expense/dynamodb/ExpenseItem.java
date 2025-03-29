package ata.unit.three.project.expense.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

@ExcludeFromJacocoGeneratedReport
@DynamoDBTable(tableName = "Expense")
public class ExpenseItem implements Comparable<ExpenseItem> {

    private String id;
    private String email;
    private String expenseDate;
    private String title;
    private Double amount;

    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return this.id;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "EmailIndex", attributeName = "Email")
    public String getEmail() {
        return this.email;
    }

    @DynamoDBAttribute(attributeName = "ExpenseDate")
    public String getExpenseDate() {
        return this.expenseDate;
    }

    @DynamoDBAttribute(attributeName = "Title")
    public String getTitle() {
        return this.title;
    }

    @DynamoDBAttribute(attributeName = "Amount")
    public Double getAmount() {
        return this.amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpenseItem)) return false;
        ExpenseItem that = (ExpenseItem) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(ExpenseItem o) {
        return this.expenseDate.compareTo(o.expenseDate);
    }
}
