package vn.techmaster.job_hunt.respository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Employer;

@Repository // Coi class này như 1 data base vì chưa kết nối jpa.
// Repository là nơi để lấy giữ liệu
public class EmployerRepo {
  private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

  public EmployerRepo() {

  }

  // Cần sinh UUID ở đây
  public Employer add(Employer employer) {
    String id = UUID.randomUUID().toString();
    employer.setId(id);
    employers.put(id, employer);
    return employer;
  }

  // Thêm list sử dụng collection (collection và list là 2 interface khác nhau)
  public Collection<Employer> getAll() {
    return employers.values();
  }

  public Employer findById(String id) {
    return employers.get(id);
  }

  // Cập nhật logo của Employer
  public void updateLogo(String id, String logo_path) {
    var emp = employers.get(id);
    emp.setLogo_path(logo_path);
    employers.put(id, emp);
  }

  // Xóa employer
  public Employer deleteById(String id) {
    return employers.remove(id);
  }

  @PostConstruct
  public void addSomeData() {
    this.add(Employer.builder().name("FPT")
        .website("https://fpt.com.vn")
        .logo_path("fpt.jpg")
        .email("hr@fpt.com.vn").build());

    this.add(Employer.builder().name("CMC")
        .website("https://cmc.com.vn")
        .logo_path("cmc.jpg")
        .email("hr@cmc.com.vn").build());

    this.add(Employer.builder().name("Amazon")
        .website("https://amazon.com")
        .logo_path("amazon.jpg")
        .email("hr@amazon.com.vn").build());

    this.add(Employer.builder().name("Google")
        .website("https://google.com")
        .logo_path("google.webp")
        .email("hr@google.com.vn").build());

  }

}
