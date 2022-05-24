package vn.techmaster.jobhunt.controller;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerRepository repo;

    @GetMapping("/list")
    public String getEmployers(Model model) {
        model.addAttribute("employers", repo.getEmployers());
        return "employers";
    }

    @GetMapping("/add")
    public String createEmployer(Model model) {
        model.addAttribute("employer", new Employer());
        return "add_emp";
    }

    @PostMapping("/save")
    public String saveEmployer(Employer employer, RedirectAttributes redirect) {
        repo.addEmployer(employer);
        redirect.addFlashAttribute("success", "Saved employer successfully!");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editEmployer(@PathVariable("id") String id, Model model) {
        model.addAttribute("employer", repo.findById(id));
        return "edit_emp";
    }

    @PostMapping("/update")
    public String updateEmployer(Employer employer, RedirectAttributes redirect) {
        repo.updateEmployer(employer.getId(), employer);
        redirect.addFlashAttribute("success", "Updated employer successfully!");
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String findDetele(@PathVariable("id") String id, Model model) {
        model.addAttribute("employer", repo.findById(id));
        return "delete_emp";
    }

    @PostMapping("/delete")
    public String deteleEmployer(Employer employer, RedirectAttributes redirect) {
        repo.deleteEmployer(employer.getId());
        redirect.addFlashAttribute("success", "Deleted employer successfully!");
        return "redirect:/";
    }
}
