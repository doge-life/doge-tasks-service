package org.doge.services;

import org.doge.domain.TodoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TodosService {

    @Autowired
    private TodoRepository repository;

    public List<TodoDomain> getAllTodos()
    {
        Iterable<Todo> todosFromDb = repository.findAll();
        return StreamSupport.stream(todosFromDb.spliterator(), false)
            .map(this::translate)
            .collect(Collectors.toList());
    }

    private TodoDomain translate(Todo todo) {
        return new TodoDomain(todo.getId(), todo.getName(), todo.isCompleted());
    }
}
