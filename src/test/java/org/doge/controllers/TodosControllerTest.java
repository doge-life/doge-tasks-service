package org.doge.controllers;

import org.doge.domain.Todo;
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

import static org.hamcrest.core.Is.is;
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
        List<Todo> expectedTodos = Arrays.asList(
            Todo.builder().id(1).name("Hello").build(),
            Todo.builder().id(2).name("World").build()
        );
        when(todosService.getAllTodos()).thenReturn(expectedTodos);

        List<Todo> actualTodos = subject.getAllTodos();

        Assert.assertThat(actualTodos, is(expectedTodos));
    }
}