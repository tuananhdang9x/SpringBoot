package vn.techmaster.job_hunt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Cho phép khởi tạo đối tượng bằng cách nối chuỗi
public class Employer {
  private String id;
  private String name;
  private String logo_path;
  private String website;
  private String email;
}
