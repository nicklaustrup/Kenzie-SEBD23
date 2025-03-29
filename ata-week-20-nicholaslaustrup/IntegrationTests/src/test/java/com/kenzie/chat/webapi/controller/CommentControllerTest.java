package com.kenzie.chat.webapi.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.kenzie.chat.webapi.IntegrationTest;
import com.kenzie.chat.webapi.controller.model.CommentCreateRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }

    @Test
    public void can_create_comment() throws Exception {
        // Fix this test

        // GIVEN
        String username = mockNeat.strings().get();

        UserCreateRequest userRequest = new UserCreateRequest();
        userRequest.setName(mockNeat.names().get());
        userRequest.setEmail(mockNeat.emails().get());
        userRequest.setUsername(username);

        CommentCreateRequest commentRequest = new CommentCreateRequest();
        commentRequest.setOwner(username);
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());

        // WHEN
        queryUtility.userControllerClient.createUser(userRequest);
        queryUtility.commentControllerClient.addComment(commentRequest)
        // THEN
                .andExpect(status().isOk());
    }
    @Test
    public void newUser_nullData_unsuccessful() throws Exception {
        // Fix this test

        // GIVEN
        String username = mockNeat.strings().get();

        UserCreateRequest userRequest = new UserCreateRequest();
        userRequest.setName(mockNeat.names().get());
        userRequest.setEmail(mockNeat.emails().get());
        userRequest.setUsername(null);

        // WHEN
//        queryUtility.commentControllerClient
        Assertions.assertThrows(NestedServletException.class, ()-> queryUtility.userControllerClient.createUser(userRequest));
    }
    @Test
    public void newUser_invalidData_unsuccessful() throws Exception {
        // Fix this test

        // GIVEN

        UserCreateRequest userRequest = new UserCreateRequest();
        userRequest.setName(mockNeat.names().get());
        userRequest.setEmail(mockNeat.emails().get());
        userRequest.setUsername("");

        // WHEN
        Assertions.assertThrows(NestedServletException.class, ()-> queryUtility.userControllerClient.createUser(userRequest));
    }
}

