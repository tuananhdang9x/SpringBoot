package vn.techmaster.job_hunt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.techmaster.job_hunt.model.City;
import vn.techmaster.job_hunt.model.Job;
import vn.techmaster.job_hunt.request.JobRequest;
import vn.techmaster.job_hunt.respository.JobRepo;

@Controller
@RequestMapping("/job")
public class JobController {
  @Autowired
  private JobRepo jobRepo;

  @GetMapping
  public String listAllJobs(Model model) {
    model.addAttribute("jobs", jobRepo.getAll());
    return "jobs";
  }

  @GetMapping("/add")
  public String showAddJobForm(Model model, @RequestParam("emp_id") String emp_id) {
    Job job = new Job();
    job.setEmp_id(emp_id);
    model.addAttribute("job", job);
    model.addAttribute("cities", City.values());

    return "job_add";
  }

  @PostMapping(value = "/add")
  public String addJob(@Valid @ModelAttribute("job") JobRequest jobRequest,
      BindingResult result,
      Model model) {
    jobRepo.addJob(Job.builder()
        .title(jobRequest.title())
        .description(jobRequest.description())
        .city(jobRequest.city())
        .build());
    return "redirect:/job";
  }

  @GetMapping("/{emp_id}")
  public String listJobById(Model model,
      @PathVariable("emp_id") String emp_id) {
    model.addAttribute("job", jobRepo.findByEmpId(emp_id));
    return "emp_job";
  }
}
