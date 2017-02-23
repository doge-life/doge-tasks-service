package org.doge.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodosControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getTodos() throws Exception {
        mvc.perform(get("/todos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("[?(@.id == 1)].name", containsInAnyOrder("This is the first Todo")))
            .andExpect(jsonPath("[?(@.id == 1)].completed", containsInAnyOrder(false)))
            .andExpect(jsonPath("[?(@.id == 2)].name", containsInAnyOrder("This is a completed Todo")))
            .andExpect(jsonPath("[?(@.id == 2)].completed", containsInAnyOrder(true)));
    }
}
