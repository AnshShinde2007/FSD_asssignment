package com.todo.demo.controller;

import com.todo.demo.model.Todo;
import com.todo.demo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // GET all todos
    @GetMapping("/all")
    public List<Todo> getAll() {
        return service.getAllTodos();
    }

    // GET todo by ID
    @GetMapping("/get/{id}")
    public Todo getById(@PathVariable Long id) {
        return service.getTodoById(id);
    }

    // CREATE todo
    @PostMapping("/create")
    public Todo create(@RequestBody Todo todo) {
        return service.createTodo(todo);
    }

    // UPDATE todo
    @PutMapping("/update/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        return service.updateTodo(id, todo);
    }

    // DELETE todo
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTodo(id);
    }
}