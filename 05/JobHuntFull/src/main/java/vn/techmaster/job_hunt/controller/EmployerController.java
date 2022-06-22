package vn.techmaster.job_hunt.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.job_hunt.model.Employer;
import vn.techmaster.job_hunt.request.EmployerRequest;
import vn.techmaster.job_hunt.respository.EmployerRepo;

import vn.techmaster.job_hunt.service.StorageService;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
  // Autowired -> Tự kết nối đến
  // Trỏ đến repository để lấy dữ liệu
  @Autowired
  private EmployerRepo employerRepo;

  @Autowired
  private StorageService storageService;

  @GetMapping
  public String listAllEmployers(Model model) {
    model.addAttribute("employers", employerRepo.getAll());
    return "employers";
  }

  @GetMapping(value = "/{id}")
  public String showEmployerDetailByID(Model model, @PathVariable String id) {
    model.addAttribute("employer", employerRepo.findById(id));
    return "employer";
  }

  @GetMapping(value = "/add")
  public String addEmployerForm(Model model) {
    model.addAttribute("employer", new EmployerRequest("", "", "", "", null));
    return "employer_add";
  }

  // Sau khi thêm employer thì quay trở lại trang chủ
  // ModelAttribute là phần sever sẽ gửi lên cho mình, bóc tách dữ liệu body
  // BindingResult là nếu form gửi đúng thì BindingResult sẽ báo success
  // Kết quả của Valid sẽ đưa vào BindingResult
  @PostMapping(value = "/add", consumes = { "multipart/form-data" })
  public String addEmployer(@Valid @ModelAttribute("employer") EmployerRequest employerRequest,
      BindingResult result,
      Model model) {
    if (employerRequest.logo().getOriginalFilename().isEmpty()) {
      result.addError(new FieldError("employer", "logo", "Logo is required"));
    }

    // Nêú có lỗi thì trả về trình duyệt
    if (result.hasErrors()) {
      return "employer_add";
    }

    // Thêm vào cơ sở dữ liệu
    Employer emp = employerRepo.add(Employer.builder()
        .name(employerRequest.name())
        .website(employerRequest.website())
        .email(employerRequest.email())
        .build());

    // Lưu logo vào ổ cứng
    try {
      String logoFileName = storageService.saveFile(employerRequest.logo(), emp.getId());
      employerRepo.updateLogo(emp.getId(), logoFileName);
    } catch (IOException e) {
      // Nếu lưu file bị lỗi thì hãy xoá bản ghi Employer
      e.printStackTrace();
    }
    return "redirect:/employer";
  }

  @GetMapping(value = "/delete/{id}")
  public String deleteEmployerByID(@PathVariable String id) {
    Employer emp = employerRepo.deleteById(id);
    storageService.deleteFile(emp.getLogo_path());
    return "redirect:/employer";
  }

  @GetMapping("/update/{id}")
  public String editEmployer(@PathVariable("id") String id, Model model) {
    model.addAttribute("employer", employerRepo.findById(id));
    return "employer_update";
  }

  @PostMapping("/update/{id}")
  public String updateEmployer(@Valid @ModelAttribute("employer") EmployerRequest employerRequest, Employer employer,
      BindingResult result,
      Model model) {
    if (employerRequest.logo().getOriginalFilename().isEmpty()) {
      result.addError(new FieldError("employer", "logo", "Logo is required"));
    }

    // Nêú có lỗi thì trả về trình duyệt
    if (result.hasErrors()) {
      return "employer_add";
    }

    // Thêm vào cơ sở dữ liệu
    Employer emp = employerRepo.add(Employer.builder()
        .name(employerRequest.name())
        .website(employerRequest.website())
        .email(employerRequest.email())
        .build());

    // Lưu logo vào ổ cứng
    try {
      String logoFileName = storageService.saveFile(employerRequest.logo(), emp.getId());
      employerRepo.updateLogo(emp.getId(), logoFileName);
    } catch (IOException e) {
      // Nếu lưu file bị lỗi thì hãy xoá bản ghi Employer
      e.printStackTrace();
    }
    employerRepo.updateEmployer(employer);
    return "redirect:/employer";
  }

}
