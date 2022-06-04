package com.firstproject.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.firstproject.exception.BadRequestException;

@Service
public class ColorService {

    // Cần 1 biến tái sử dụng Random
    private Random random = new Random();

    // Cần 1 switch case để khi nhập type từ requestparam nó sẽ mapping tới các
    // function
    // type = 1 -> random color name
    // type = 2 -> random Hex color
    // type = 3 -> random RGB color

    public String getRandomColor(int type) {

        return switch (type) {
            case 1 -> getRandomName(); // Không sử dụng case 1: getRandom mà sử dụng case 1 -> getRandom// Sử dụng ->
                                       // khi gọi tới một hàm
            case 2 -> getRandomHexColor();
            case 3 -> getRandomRgbColor();
            default -> throw new BadRequestException("Type không hợp lệ");// Trả ra 1 exception
        };
    }

    public String getRandomName() {
        List<String> colorName = Arrays.asList("black",
                "silver",
                "gray",
                "white",
                "maroon",
                "red",
                "purple",
                "fuchsia",
                "green",
                "lime",
                "olive",
                "yellow",
                "navy",
                "blue",
                "teal",
                "aqua");
        return colorName.get(random.nextInt(colorName.size()));
    }

    public String getRandomHexColor() {
        int nextInt = random.nextInt(0xffffff + 1);
        return String.format("#%06x", nextInt);

    }

    public String getRandomRgbColor() {
        return String.format("rgb(%d, %d, %d)", random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

}
