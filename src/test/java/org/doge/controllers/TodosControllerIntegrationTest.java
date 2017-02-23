package org.doge.controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.doge.domain.TodoDomain;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodosControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getTodos() throws Exception {
        MvcResult result = mvc.perform(get("/todos"))
            .andExpect(status().isOk())
            .andReturn();

        JsonParser parser = new JsonFactory().createParser(result.getResponse().getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        List<TodoDomain> todosReturned = Arrays.asList(mapper.readValue(parser, TodoDomain[].class));

        List<TodoDomain> expectedTodos = Arrays.asList(
            new TodoDomain(1, "This is the first Todo", false),
            new TodoDomain(2, "This is a completed Todo", true)
        );
        Assert.assertThat(todosReturned, is(expectedTodos));
    }
}
