package com.kenzie.library;


import com.kenzie.library.model.LibraryBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import com.kenzie.library.model.Book;

public class BookTest {


	//Check Book class is abstract
	@DisplayName("Check that Book class is defined as Abstract")
	@Test
	void checkBookClassAbstract() throws Exception {
		Class<Book> clazz = Book.class;

		Assertions.assertTrue(Modifier.isAbstract(clazz.getModifiers()));
	}


	@DisplayName("Check that Book class has required properties")
	@Test
	void bookClassPropertiesExist() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		try {
			//return list of properties
			Field fieldList[] = Book.class.getDeclaredFields();

			//contains title and property
			boolean foundTitle = false;
			boolean foundAuthor = false;

			for (int i = 0; i < fieldList.length ; i++) {
				if(fieldList[i].getName().equals("title")){
					foundTitle=true;
				}
				if(fieldList[i].getName().equals("author")){
					foundAuthor=true;
				}
			}
			assertTrue(foundTitle, "Property [title] must be defined");
			assertTrue(foundAuthor, "Property [author] must be defined");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Book abstract class must be defined with required properties");
		}
	}

	@DisplayName("Check that Book class has required methods")
	@Test
	void bookClassMethodsExist() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		try {
			//return list of properties
			Method[] methodList = Book.class.getDeclaredMethods();

			//contains title and property
			boolean foundConvertBook = false;
			boolean foundBookInfo= false;

			for (int i = 0; i < methodList.length ; i++) {
				if(methodList[i].getName().equals("convertBookToString")){
					foundConvertBook=true;
				}
				if(methodList[i].getName().equals("setBookInfo")){
					foundBookInfo=true;
				}
			}
			assertTrue(foundConvertBook, "Method [convertBookToString] must be defined");
			assertTrue(foundBookInfo, "Method [setBookInfo] must be defined");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Book abstract class must be defined with required properties");
		}
	}

}

