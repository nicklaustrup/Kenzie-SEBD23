package com.kenzie.chat.webapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.chat.webapi.IntegrationTest;
import com.kenzie.chat.webapi.controller.model.CommentCreateRequest;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Use this class to create your integration tests for the ContentModerationController
@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentModerationControllerTest {


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
    public void checkSpam_moreCommentsWithinProbation_deactivated() throws Exception {

        // GIVEN
        String username = mockNeat.strings().get();
        String email = mockNeat.emails().get();
        String name = mockNeat.names().get();

        //create User
        UserCreateRequest userRequest = new UserCreateRequest();
        userRequest.setName(name);
        userRequest.setEmail(email);
        userRequest.setUsername(username);

        queryUtility.userControllerClient.createUser(userRequest).andExpect(status().isOk());

        //Create Comment
        CommentCreateRequest commentRequest = new CommentCreateRequest();
        commentRequest.setOwner(username);
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());

        for (int i = 0; i < 20; i++) {
            queryUtility.commentControllerClient.addComment(commentRequest).andExpect(status().isOk());
        }

        queryUtility.contentModerationControllerClient.checkSpam()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.username").value(username))
                .andExpect(jsonPath("$.*.email").value(email))
                .andExpect(jsonPath("$.*.name").value(name))
                .andExpect(jsonPath("$.*.active").value(false))
        ;


    }

    @Test
    public void checkSpam_lessCommentsWithinProbation_staysActivate() throws Exception {

        // GIVEN
        String name = mockNeat.names().get();
        String username = name;
        String email = mockNeat.emails().get();

        //create User
        UserCreateRequest userRequest = new UserCreateRequest();
        userRequest.setName(name);
        userRequest.setEmail(email);
        userRequest.setUsername(username);

        queryUtility.userControllerClient.createUser(userRequest).andExpect(status().isOk());

        //Create Comment
        CommentCreateRequest commentRequest = new CommentCreateRequest();
        commentRequest.setOwner(username);
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());

        for (int i = 0; i < 2; i++) {
            queryUtility.commentControllerClient.addComment(commentRequest).andExpect(status().isOk());
        }

        ResultActions result = queryUtility.contentModerationControllerClient.checkSpam()
                .andExpect(status().isOk())
        ;

    }
}
