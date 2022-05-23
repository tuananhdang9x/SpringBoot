package com.template.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.template.web.dto.StudentRequest;
import com.template.web.model.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @PostMapping("/bmi")
    public Double bmiCaculation(@RequestParam("weight") Double weight, @RequestParam("height") Double height) {
        Double bmi = weight / (height * height);
        return bmi;
    }

    private static ArrayList<Student> listStudent() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Tuan Anh", 25));
        list.add(new Student("Hoang", 25));
        list.add(new Student("Luong", 25));

        return list;
    }

    @GetMapping("/get")
    public ArrayList<Student> getAllStudent() {
        return listStudent();
    }

    @PostMapping("/add")
    public ArrayList<Student> addStudent(@RequestBody StudentRequest student) {
        listStudent().add(new Student(student.name(), student.age()));
        return listStudent();
    }

}
