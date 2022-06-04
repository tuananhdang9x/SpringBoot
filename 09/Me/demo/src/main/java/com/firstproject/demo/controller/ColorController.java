package com.firstproject.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.demo.service.ColorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ColorController {
    // Khai báo ColorService
    private final ColorService colorService;

    @GetMapping("/random-color")
    public String getRandom(@RequestParam("type") int type) {
        return colorService.getRandomColor(type);
    }

    // Sau khi tạo xong Api thì sử dụng javascript gọi api đó.
    // -> Khởi tạo frontend

}