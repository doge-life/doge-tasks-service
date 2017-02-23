package org.doge.controllers;

import org.doge.domain.TodoDomain;
import org.doge.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodosController {

    @Autowired
    private TodosService todosService;

    @RequestMapping(value = "/todos")
    public List<TodoDomain> getAllTodos() {
        return todosService.getAllTodos();
    }


}
