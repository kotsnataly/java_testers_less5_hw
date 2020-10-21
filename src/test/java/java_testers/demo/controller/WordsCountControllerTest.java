package java_testers.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java_testers.demo.model.Words;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WordsCountControllerTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testWordsCounter1() throws Exception {
        TextsStripper textsStripper = new TextsStripper();
        textsStripper.openFile("C:\\Users\\shakja\\IdeaProjects\\lesson4hw\\src\\main\\resources\\The Return of the King.txt");
        MvcResult mvcResult = this.mockMvc.perform(post("/books")
                .content("This tale grew in the telling")
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //проверяем ответ на сушествование.
        Assertions.assertNotNull(result);

    }

}