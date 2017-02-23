package org.doge.controllers;

import org.doge.domain.TodoDomain;
import org.doge.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TodosController {

    @Autowired
    private TodosService todosService;

    public List<TodoDomain> getAllTodos() {
        return todosService.getAllTodos();
    }


}
