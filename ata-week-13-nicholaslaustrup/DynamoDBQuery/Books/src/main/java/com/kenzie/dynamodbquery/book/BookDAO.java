package com.kenzie.dynamodbquery.book;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDAO {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Book objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public BookDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Uses the query() method to retrieve all the items from the BooksRead table that have a given employee id value.
     * @param employeeId the employee ID
     * @return the PaginatedQueryList that is returned from the query
     */
    public List<Book> getBooksReadByEmployee(String employeeId) {
        //Create object for DB query
        Book book = new Book();
        book.setId(employeeId);

        //Query DB
        DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>()
            .withHashKeyValues(book);

        //Save query result into paginated list
        PaginatedQueryList<Book> bookList = mapper.query(Book.class, queryExpression);

        //return the list
        return bookList;
    }

    /**
     * Uses the query() method to retrieve all the items from the BooksRead table that have a given employee id value.
     * @param employeeId the employee ID
     * @param exclusiveStartAsin the Asin to begin at
     * @param limit the number of results returned each time
     * @return the List from the QueryResultPage that is returned from the query
     */
    public List<Book> getBooksReadByEmployee(String employeeId, String exclusiveStartAsin, int limit){
        //Create object for DB query
        Book book = new Book();
        book.setId(employeeId);

        //Create the starting point of new paginated list results
        Map<String, AttributeValue> exclusiveStartKey = null;
        if (exclusiveStartAsin != null) {
            exclusiveStartKey = new HashMap<>();
            exclusiveStartKey.put("id", new AttributeValue().withS(employeeId));
            exclusiveStartKey.put("asin", new AttributeValue().withS(exclusiveStartAsin));
        }

        //Query DB with exclusive start key and limit
        DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>()
                .withHashKeyValues(book)
                .withExclusiveStartKey(exclusiveStartKey)
                .withLimit(limit);

        //Save query result into paginated list
        QueryResultPage<Book> bookQueryResults = mapper.queryPage(Book.class, queryExpression);
        List<Book> bookList = bookQueryResults.getResults();

        //return the list
        return bookList;
    }
}
