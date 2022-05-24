package vn.techmaster.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.techmaster.jobhunt.repository.EmployerRepository;

@Controller
public class EmployerController {
    @Autowired
    private EmployerRepository repo;

    @GetMapping("/employers")
    public String getEmployers(Model model) {
        model.addAttribute("employers", repo.getEmployers());
        return "employers";
    }
}
