package com.kenzie.library;


import com.kenzie.library.model.Book;
import com.kenzie.library.model.LibraryBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LibraryBookTest {


	//Check Book class is abstract
	@DisplayName("Check that LibraryBook class is not defined as abstract")
	@Test
	void checkLibraryBookClassAbstract() throws Exception {
		Class<LibraryBook> clazz = LibraryBook.class;

		Assertions.assertFalse(Modifier.isAbstract(clazz.getModifiers()),"Should not be defined as abstract");
	}

	//Check that properties are defined

	//Check that methods exist
	@DisplayName("Check that LibraryBook class has required properties")
	@Test
	void libraryBookClassPropertiesExist() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		try {
			//return list of properties
			Field[] fieldList = LibraryBook.class.getDeclaredFields();

			//contains title and property
			boolean foundIsbn = false;
			boolean foundSubjectList = false;

			for (int i = 0; i < fieldList.length ; i++) {
				if(fieldList[i].getName().equals("isbn")){
					foundIsbn=true;
				}
				if(fieldList[i].getName().equals("subjectList")){
					foundSubjectList=true;
				}
			}
			assertTrue(foundIsbn, "Property [isbn] must be defined");
			assertTrue(foundSubjectList, "Property [subjectList] must be defined");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Book abstract class must be defined with required properties");
		}
	}

	@DisplayName("Check that LibraryBook class has required methods")
	@Test
	void libraryBookClassMethodsExist() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		try {
			//return list of properties
			Method[] methodList = LibraryBook.class.getDeclaredMethods();

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

