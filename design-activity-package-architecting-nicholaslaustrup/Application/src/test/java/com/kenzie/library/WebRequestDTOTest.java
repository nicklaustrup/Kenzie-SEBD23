package com.kenzie.library;


import com.kenzie.library.http.model.WebRequestDTO;
import com.kenzie.library.http.service.HTTPRequestClient;
import com.kenzie.library.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class WebRequestDTOTest {


	@DisplayName("Check that WebRequestDTO class is defined as abstract")
	@Test
	void checkWebRequestDTOAbstract() throws Exception {
		Class<WebRequestDTO> clazz = WebRequestDTO.class;

		Assertions.assertTrue(Modifier.isAbstract(clazz.getModifiers()),"Should be defined as abstract");
	}

	@DisplayName("Check that WebRequestDTO class has required properties")
	@Test
	void webRequestPropertiesExist() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		try {
			//return list of properties
			Field fieldList[] = WebRequestDTO.class.getDeclaredFields();

			int numFound;
			ArrayList<?> documents;

			//contains title and property
			boolean foundNumFound = false;
			boolean foundDocuments = false;

			for (int i = 0; i < fieldList.length ; i++) {
				if(fieldList[i].getName().equals("numFound")){
					foundNumFound=true;
				}
				if(fieldList[i].getName().equals("documents")){
					foundDocuments=true;
				}
			}
			assertTrue(foundNumFound, "Property [numFound] must be defined");
			assertTrue(foundDocuments, "Property [documents] must be defined");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("WebRequestDTO abstract class must be defined with required properties");
		}
	}

}

