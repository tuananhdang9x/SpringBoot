package vn.techmaster.job_hunt.respository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.City;
import vn.techmaster.job_hunt.model.Job;

@Repository
public class JobRepo {
  private ConcurrentHashMap<String, Job> jobs = new ConcurrentHashMap<>();

  public Collection<Job> getAll() {
    return jobs.values();
  }

  public Job addJob(Job job) {
    String id = UUID.randomUUID().toString();
    job.setId(id);
    jobs.put(id, job);
    return job;
  }

  public Collection<Job> findByEmpId(String emp_id) {
    List<Job> empJobs = jobs.values().stream().filter(job -> job.getEmp_id().equals(emp_id))
        .collect(Collectors.toList());

    return empJobs;
  }

  public Job deleteById(String id) {
    return jobs.remove(id);
  }

  public void update(Job job) {
    jobs.put(job.getId(), job);
  }

  @PostConstruct
  public void addSomeData() {
    this.addJob(Job.builder().title("Tuyển dụng Amazon")
        .description("Backend developer")
        .city(City.HaNoi)
        .build());
    this.addJob(Job.builder().title("Tuyển dụng Cmc")
        .description("Backend developer")
        .city(City.DaNang)
        .build());
    this.addJob(Job.builder().title("Tuyển dụng Fpt")
        .description("Backend developer")
        .city(City.HaiPhong)
        .build());
    this.addJob(Job.builder().title("Tuyển dụng Google")
        .description("Backend developer")
        .city(City.HoChiMinh)
        .build());
  }
}
