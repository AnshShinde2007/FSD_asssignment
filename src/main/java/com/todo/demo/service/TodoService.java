package com.todo.demo.service;

import com.todo.demo.model.Todo;
import com.todo.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getAllTodos() {
        return repo.findAll();
    }

    public Todo getTodoById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Todo createTodo(Todo todo) {
        return repo.save(todo);
    }

    public Todo updateTodo(Long id, Todo updated) {
        return repo.findById(id).map(todo -> {
            todo.setTitle(updated.getTitle());
            todo.setDescription(updated.getDescription());
            todo.setCompleted(updated.isCompleted());
            return repo.save(todo);
        }).orElse(null);
    }

    public void deleteTodo(Long id) {
        repo.deleteById(id);
    }
}