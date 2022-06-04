//Sau khi tạo xong các nút bên index.html -> tạo lắng nghe sự kiện khi click vào sự kiện thì mapping tới link api tương ứng

// Lấy ra các nút

const body = document.body;

const btnRandomName = document.getElementById("btn-random-name-color");
const btnRandomHexColor = document.getElementById("btn-random-hex-color");
const btnRandomRgbColor = document.getElementById("btn-random-rgb-color");

// Lắng nghe sự kiện
btnRandomName.addEventListener("click", async () => {
    try {
        // Khi click thì nó sẽ gọi tới api thông qua axios
        let res = await axios.get("http://localhost:8089/random-color?type=1");
        //Đổi màu cho phần body
        const color = res.data;
        body.style.backgroundColor = color;
        console.log(res);
    } catch (error) {
        const message = error.response.data.message
        alert(message);
    }
})

// Vì 2 domain khác nhau nên phải xử lý config => Tạo config trong hàm main Springboot

btnRandomHexColor.addEventListener("click", async () => {
    try {
        let res = await axios.get("http://localhost:8089/random-color?type=2");
        const color = res.data;
        body.style.backgroundColor = color;
        console.log(res);
    } catch (error) {
        const message = error.response.data.message
        alert(message);
    }
})

btnRandomRgbColor.addEventListener("click", async () => {
    try {
        let res = await axios.get("http://localhost:8089/random-color?type=3");
        const color = res.data;
        body.style.backgroundColor = color;
        console.log(res);
    } catch (error) {
        const message = error.response.data.message
        alert(message);
    }
})

// Kết nối thành công giữa api từ Springboot và frontend.
//=> Bắt lỗi


