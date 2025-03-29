package com.kenzie.library;


import com.kenzie.library.http.service.HTTPRequestClient;
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

public class HTTPRequestClientTest {


	//Check if HttpRequestClient is interface
	@DisplayName("Check that HTTPRequestClient is defined as interface")
	@Test
	void checkHTTPRequestClientAbstract() throws Exception {
		Class<HTTPRequestClient> clazz = HTTPRequestClient.class;

		Assertions.assertTrue(Modifier.isAbstract(clazz.getModifiers()),"Should be defined as interface");
	}

	@DisplayName("Check that HTTPRequestClient interface has required methods")
	@Test
	void HTTPRequestClientClassMethodsExist() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		try {
			//return list of properties
			Method[] methodList = HTTPRequestClient.class.getDeclaredMethods();

			//contains title and property
			boolean foundSendGet = false;
			boolean foundCheckError= false;
			boolean foundGetResponse = false;

			for (int i = 0; i < methodList.length ; i++) {
				if(methodList[i].getName().equals("sendGET")){
					foundSendGet=true;
				}
				if(methodList[i].getName().equals("checkError")){
					foundCheckError=true;
				}
				if(methodList[i].getName().equals("getResponse")){
					foundGetResponse=true;
				}

			}
			assertTrue(foundSendGet, "Method [sendGET] must be defined");
			assertTrue(foundCheckError, "Method [checkError] must be defined");
			assertTrue(foundGetResponse, "Method [getResponse] must be defined");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Book abstract class must be defined with required properties");
		}
	}

}

