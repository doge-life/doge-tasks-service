package org.doge.controllers;

import org.doge.domain.Todo;
import org.doge.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TodosController {

    @Autowired
    private TodosService todosService;

    public List<Todo> getAllTodos() {
        return todosService.getAllTodos();
    }


}
