package com.todolist.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.todolist.demo.model.Todo;
import com.todolist.demo.request.CreateTodoRequest;
import com.todolist.demo.request.UpdateTodoRequest;
import com.todolist.demo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class TodoController {
    // inject qua contructor có thể sử dụng autowired

    private final TodoService todoService;

    // Lấy danh sách tẩt cả công việc
    @GetMapping("/todos")
    public List<Todo> getTodo(@RequestParam(required = false, defaultValue = "") String status) {

        return todoService.getTodos(status);
    }

    // Lấy công việc theo id
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService.getTodoById(id);
    }

    // Tạo mới công việc
    @PostMapping("/todos")
    // @ResponseStatus(HttpStatus.CREATED) // Trả về status khi thành công 201 tạo
    // thành công, k tạo thì sẽ là 200
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoRequest request) {
        Todo todo = todoService.createTodo(request);
        return new ResponseEntity<Todo>(todo, HttpStatus.CREATED); // Mặc định trả về là json , ví dụ khi trả về ảnh thì
                                                                   // có thể sử dụng responentity
    }

    // Cập nhật công việc
    // Có thể chỉnh sửa tiêu đề và trạng thái công việc

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    // Xóa công việc
    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
