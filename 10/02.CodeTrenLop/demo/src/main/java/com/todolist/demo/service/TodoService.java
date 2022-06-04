package com.todolist.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.demo.exception.NotFoundException;
import com.todolist.demo.model.Todo;
import com.todolist.demo.request.CreateTodoRequest;
import com.todolist.demo.request.UpdateTodoRequest;

@Service
public class TodoService {
    private List<Todo> todos;

    public TodoService() {
        Random rd = new Random();
        todos = new ArrayList<>();

        todos.add(new Todo(rd.nextInt(100), "Learning code", false));
        todos.add(new Todo(rd.nextInt(100), "Watching movie", true));
        todos.add(new Todo(rd.nextInt(100), "Running", false));
    }

    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "true" -> todos.stream().filter(Todo::isStatus).collect(Collectors.toList());

            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());

            default -> todos;
        };
    }

    // Kiểm xoát giá trị null
    // Sử dụng Helper mothod: Tìm kiếm một todo theo id -> Khi không biết todo tìm
    // kiếm có tồn tại hay k
    public Optional<Todo> findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }

    public Todo getTodoById(int id) {
        Optional<Todo> todoOptional = findById(id);
        // if (todoOptional.isPresent()) { // Có tồn tại
        // return todoOptional.get();
        // }

        // throw new NotFoundException("Không tìm thấy công việc có id=" + id);

        return todoOptional.orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id=" + id);
        });
    }

    public Todo createTodo(CreateTodoRequest request) {
        // Có thể validate title nếu để trống
        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(100), request.getTitle(), false);
        todos.add(todo);
        return todo;
    }

    // Update todo
    public Todo updateTodo(int id, UpdateTodoRequest request) {
        // Kiểm tra công việc có tồn tại hay không
        // Optional<Todo> todoOptional = findById(id);
        // if (todoOptional.isEmpty()) { // Không tồn tại
        // throw new NotFoundException("Không tìm thấy công việc có id=" + id);
        // }
        // Cập nhật công việc theo thông tin từ request
        Todo todo = getTodoById(id); // đoạn trên đã thực hiện kiểm tra rồi
        todo.setTitle((request.getTitle()));
        todo.setStatus((request.isStatus()));

        // Trả về công việc sau khi cập nhật thành công
        return todo;
    }

    // Functional interface có sẵn trong java
    public void deleteTodo(int id) {
        Todo todo = getTodoById(id);
        todos.removeIf(t -> t.getId() == todo.getId());
    }
}
