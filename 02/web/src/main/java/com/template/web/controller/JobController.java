package com.template.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.template.web.dto.JobRequest;
import com.template.web.dto.LocationRequest;
import com.template.web.model.Job;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    private ConcurrentHashMap<String, Job> jobs;

    public JobController() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("1a",
                new Job("1a", "Kỹ sư ME", "Mechanical Engineer", new String[] { "Hồ Chí Minh", "Hà Nội" },
                        8000000,
                        15000000, "tuyendung@gmail.com"));
        jobs.put("2a", new Job("2a", "Backend java", "Backend Developer", new String[] { "Hà Nội" }, 12000000,
                30000000, "tuyendung1@gmail.com"));
        jobs.put("3a", new Job("3a", "Frontend", "Fronted Developer", new String[] { "Hà Nội" }, 10000000,
                20000000, "tuyendung2@gmail.com"));
        jobs.put("4a", new Job("4a", "Designer", "Photoshop", new String[] { "Đà Nẵng" }, 9000000,
                15000000, "tuyendung3@gmail.com"));
        jobs.put("5a", new Job("5a", "Editor", "Edit Video", new String[] { "Hải Phòng" }, 13000000,
                25000000, "tuyendung4@gmail.com"));
    }

    @GetMapping("/get")
    public List<Job> getAllJob() {
        return jobs.values().stream().toList();
    }

    @PostMapping("/add")
    public Job createJob(@RequestBody JobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job newJob = new Job(uuid, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email());
        jobs.put(uuid, newJob);
        return newJob;
    }

    @DeleteMapping("/delete")
    public List<Job> deleteById(@RequestParam("id") String id) {
        jobs.remove(id);
        return jobs.values().stream().toList();
    }

    @PutMapping("/update")
    public Job updateById(@RequestBody JobRequest jobRequest, @RequestParam("id") String id) {
        Job jobUpdate = new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email());
        jobs.put(id, jobUpdate);
        return jobUpdate;
    }

    // http://localhost:8080/job/sortbylocation
    // Sắp xếp danh sách job theo thành phố tuyển
    @GetMapping("/sortbylocation")
    public List<Job> sortByLocation(@RequestParam("location") String location) {
        List<Job> result = new ArrayList<>();

        for (var row : jobs.entrySet()) {
            for (String p : row.getValue().getLocation()) {
                if (p.equals(location)) {
                    result.add(row.getValue());
                }
            }
        }
        return result;
    }

    // http://localhost:8080/job/salary/{salary}
    // Tìm các job mà {salary} trong khoảng min_salary và max_salary
    @GetMapping("/salary/{salary}")
    public List<Job> sortBySalary(@PathVariable("salary") int salary) {
        List<Job> result = new ArrayList<>();
        for (var row : jobs.entrySet()) {
            if (row.getValue().getMin_salary() < salary && salary < row.getValue().getMax_salary()) {
                result.add(row.getValue());
            }
        }
        return result;
    }

    // http://localhost:8080/job/keyword/{keyword}
    // Tìm các job mà title hoặc description chứa {keyword}
    @GetMapping("/keyword")
    public List<Job> sortBySalary(@RequestParam("keyword") String keyword) {
        List<Job> result = new ArrayList<>();
        for (var row : jobs.entrySet()) {
            if (String.valueOf(row.getValue().getTitle()).toLowerCase().contains(keyword.toLowerCase())
                    || String.valueOf(row.getValue().getDescription()).toLowerCase().contains(keyword.toLowerCase())) {
                result.add(row.getValue());
            }
        }
        return result;
    }

    // http://localhost:8080/job/query?location={location}&keyword={keyword}
    // Tìm các job mà title hoặc description chứa {keyword} đồng thời
    // location={location}

    @GetMapping("/")
    public List<Job> sortBySalary(@RequestParam("location") String location, @RequestParam("keyword") String keyword) {
        List<Job> result = new ArrayList<>();

        for (var row : jobs.entrySet()) {
            for (String p : row.getValue().getLocation()) {
                if (p.equals(location)) {
                    if (String.valueOf(row.getValue().getTitle()).toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(row.getValue().getDescription()).toLowerCase()
                                    .contains(keyword.toLowerCase())) {
                        result.add(row.getValue());
                    }
                }

            }
        }
        return result;
    }
}
