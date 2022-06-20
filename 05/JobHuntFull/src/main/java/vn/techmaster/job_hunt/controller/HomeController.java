package vn.techmaster.job_hunt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Sử dụng cho sever site rendering
// @Rescontroller là client site rendering
@RequestMapping("/")
public class HomeController {
  @GetMapping
  public String showHomePage() {
    return "index";
  }
}
