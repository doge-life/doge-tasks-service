package org.doge.controllers;

import org.doge.domain.TodoDomain;
import org.doge.services.TodosService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodosControllerTest {

    @InjectMocks
    private TodosController subject;

    @Mock
    private TodosService todosService;

    @Test
    public void getAllTodos() {
        List<TodoDomain> expectedTodos = Arrays.asList(
            TodoDomain.builder().id(1).name("Hello").build(),
            TodoDomain.builder().id(2).name("World").build()
        );
        when(todosService.getAllTodos()).thenReturn(expectedTodos);

        List<TodoDomain> actualTodos = subject.getAllTodos();

        Assert.assertThat(actualTodos, is(expectedTodos));
    }
}