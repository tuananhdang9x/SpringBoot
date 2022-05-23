# 1. Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : groupID, artifactID. Ý nghĩa các tham số này là gì? 
Maven sử dụng một tập hợp các thông tin định danh, để xác định duy nhất một project và chỉ định cách đóng gói project đó.
- groupId - tên duy nhất của công ty hoặc nhóm đã tạo ra project (là tên định danh duy nhất project của bạn trên toàn bộ các project trên Maven)
- artifactID - tên riêng của project (tên của file jar/war tùy cách đóng gói dự án) Ví dụ vn.techmaster techmaster-core


# 2. Tại sao phải đảo ngược tên miền trong <groupId>vn.techmaster</groupId>?
- Theo quy định groupId phải tuân theo các quy tắc tên gói(Java's package name rules) của Java. Điều này có nghĩa là nó bắt đầu với một tên đảo ngược.
- Mục đích là tạo ra được tên định danh duy nhất và tạo ra các quy tắc chung cho cách đặt tên.
Ví dụ: Bên tổ chức tạo ra project là: techmaster.vn -> groupID = vn.techmaster (tư duy đặt tên đi từ quy mô lớn đến nhỏ cũng giống như khi đặt tên package name).

# 3. SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng?
SpringBoot có 2 cơ chế quản lý thư viện (package manager) là Maven và Gradle.

# 4. File pom.xml có tác dụng gì?
POM là viết tắt của Project Object Model (Mô hình đối tượng dự án) là đơn vị công việc cơ bản trong Maven. Nó là một tệp XML chứa thông tin về dự án và chi tiết cấu hình được Maven sử dụng để xây dựng dự án. Nó chứa các giá trị mặc định cho hầu hết các dự án. Ví dụ:
- Cấu hình Target là cho thư mục build
- Thư mục soure code: src/main/java
- Thư mục source test: là src/test/java
---
XML là gì?
- XML - Extensible Markup Language ngôn ngữ đánh dấu mở rộng. Cũng giống như Json, Xml dùng để truyền dữ liệu và mô tả nhiều loại dữ liệu khác nhau. Ví dụ khi xây dựng một ứng dụng bằng PHP và một ứng dụng bằng Java thì 2 ngôn ngữ này không thế hiểu nhau nên ta sử dụng XML để trao đổi dữ liệu.
# 5. Trong file pom.xml có các thẻ dependency. Ý nghĩa của chúng là gì?
```php
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
Bên trong - bạn định nghĩa tất cả các modun và jar mà bạn sẽ yêu cầu trong project của mình. Mỗi dependency chỉ đơn giản là một Maven project khác được xác định bởi groupId, artifactId, version.

---
Tệp jar là gì?
Tệp jar - java Archive, cụ thể hơn là file nén. Chứa bên trong là nhiều tập tin khác nhau và được "đóng gói"
lại với nhau nhằm mục đích giảm dung lượng lưu trữ

# 6.  Ý nghĩa của @Controllerlà gì?
- Annotation trong Spring Boot là một dạng siêu dữ liệu cung cấp dữ liệu về một program. Nói cách khác, annotation được sử dụng để cung cấp thông tin bổ sung về một program. Các annotation không phải là một phần của ứng dụng mà chúng ta phát triển. Nó không có ảnh hưởng trục tiếp đến hoạt động của code mà họ chú thích. Nó không thay đổi hành động của chương trình đã biên dịch.
- @Component: là một annotation của class. Nó dùng để dánh dấu class Java là một bean. Một class Java được đánh dấu @Component được tìm thây trong classpath. Spring Framework chọn nó và cấu hình trong ngữ cảnh ứngdungj như một Spring Bean.
- @Controller: @Controller là một annotation ở class. Là một chuyên môn hóa của @Conponent. Nó đánh dấu class là một class để xử lý request web. Nó thường được sử dụng để phục vụ các request từ UI. Mặc định thì nó trả về một chuỗi cho biết route nào cần redirect. Nó chủ yếu được sử dụng với annotation @RequestMapping.

# 7. Ý nghĩa của @RequestMapping là gì? Nó có những tham số gì ngoài value?
Annotation @RequestMapping được sử dụng để map request với class hoặc method xử lý request đó. Annotation @RequestMapping có thể được áp dụng cho cấp độ class hoặc cấp độ method trong controller. Ngoài value thì còn các tham số sau
- method: lựa chọn phương thức http(Get,Post,Put,Delete...), ví dụ: RequestMethod.GET
- headers headers = "Accept=application/json"
- consumes: chỉ chấp nhận các request có content-type giống với giá trị khai báo bên trong consumes
- produces: kiểu dữ liệu trả về, cái nay thường chỉ dùng với các REST-AP(từ spring 3.1)

# 8. Ý nghĩa của @RequestBody khi đặt trong hàm hứng request để làm gì?
@RequestBody và @ResponseBody dùng để ánh xạ dữ liệu truyền từ client lên sever và ngược lại.
- @RequestBody được dùng để ánh xạ HttpRequest body sang một domain object tự động. Spring sẽ tự động ánh xạ dữ liệu JSON trong HttpRequest body sang một Java Type object tương ứng. Mặc định, tên và kiểu dữ liệu trong JSON phải trung khớp với tên và kiểu dữ liệu trong Java Type Object.

# 9. Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam.
@RequestParam được dùng để trích xuất dữ liệu từ request query. @PathVariable thì được dùng để trích xuất dữ liệu từ URL path.

# 10. Thứ tự các thành phần đường dẫn @PathVariable có thể hoán đổi được không?
- Không thể thay đổi thú tự các thành phần đường dẫn do phía handle request sẽ lấy sai param từ Url.
- PathVariable phải đúng thứ tự còn đối với Requestparam thì không vì nó truyền cả tên biến.

# 11. @GetMapping khác gì so với @PostMapping?

- Get: sử dụng để lấy lại thông tin từ Sever. Các yêu cầu sử dụng get chỉ nhận dữ liệu và không ảnh hưởng gì tới dữ liệu
- Post: một yêu cầu post được sử dụng để gửi dữ liệu tới Sever.
```php
@GetMapping("/addTodo")
    public String addTodo(Model model) {
        return "addTodo";
    }

Tương đương:

@RequestMapping(value = "/addTodo", method = RequestMethod.GET)
    public String addTodo(Model model) {
        return "addTodo";
    }
```

# 12. Trong các annotation @RequestMapping, @GetMapping, @PostMapping… có tham số produces = MediaType.XXXX ý nghĩa tham số này là gì?

Định dạng kiểu dữ liệu trả về dưới dạng nào.

# 13. Giải thích ý nghĩa của @RequestBody trong đoạn code dưới đây:
```php
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
    return message;
}
```

=> Để ánh xạ request message qua object là Message.

# 14. Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định.
Tại mục application.properties 
nhập: server.port=8081


