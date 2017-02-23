package org.doge.services;

import org.doge.domain.TodoDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodosServiceTest {

    @InjectMocks
    private TodosService subject;

    @Mock
    private TodoRepository repository;

    @Test
    public void GetAllTodos() {
        Iterable<Todo> returnedTodoEntities = Arrays.asList(
            new Todo(1L, "This is the first Todo", false),
            new Todo(2L, "This is the completed Todo", true)
        );
        when(repository.findAll()).thenReturn(returnedTodoEntities);
        List<TodoDomain> actualTodos = subject.getAllTodos();

        List<TodoDomain> expectedTodos = Arrays.asList(
            new TodoDomain(1, "This is the first Todo", false),
            new TodoDomain(2, "This is the completed Todo", true)
        );
        assertThat(actualTodos, is(expectedTodos));
    }
}