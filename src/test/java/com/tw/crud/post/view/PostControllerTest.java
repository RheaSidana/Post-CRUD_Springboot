package com.tw.crud.post.view;

import com.tw.crud.App;
import com.tw.crud.post.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void beforeEach() { postRepository.deleteAll(); }

    @AfterEach
    public void afterEach(){
        postRepository.deleteAll();
    }

//    @Test
//    void shouldBeAbleToCreatePost() throws Exception {
//        String bodyJson = "{" +
//                "\"authorUsername\": \"authorUsername \"," +
//                "\" authorName\": \"authorName \"," +
//                "\" content\": \"content\"," +
//                "\"audienceType\": \"audienceType\"" +
//                "}";
//
//        mockMvc.perform(
//                post("/createPost")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(bodyJson)
//                )
//                .andExpect(status().isCreated());
//    }
}