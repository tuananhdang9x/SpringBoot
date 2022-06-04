const URL_API = "http://localhost:8080/api/v1"

//Truy cập vào các thành phần
const todoListEL = document.querySelector(".todo-list");

let todos = [];

const todoOptionItemEL = document.querySelectorAll(".todo-option-item input");


//Danh sách API
const getTodoAPI = status => {
    switch (status) {
        case "all": {
            return axios.get(`${URL_API}/todos`);
        }

        case "unactive": {
            return axios.get(`${URL_API}/todos?status=false`)
        }

        case "active": {
            return axios.get(`${URL_API}/todos?status=true`)
        }

        default: {
            return axios.get(`${URL_API}/todos`)
        }
    }
}
//status="" default parameter khi không truyền vào thì nó sẽ auto rỗng
const getTodos = async (status = "") => {
    try {
        let res = await getTodoAPI(status);
        todos = res.data;
        renderTodo(todos);
        //Dữ liệu nằm trong data

    } catch (error) {
        console(error);
    }
}

const renderTodo = arr => {
    todoListEL.innerHTML = "";
    if (arr.length == 0) {
        todoListEL.innerHTML = "Không có công việc nào trong danh sách"
        return;
    }

    let html = "";
    for (let i = 0; i < arr.length; i++) {
        const t = arr[i];
        html +=
            `
        <div class="todo-item ${t.status ? "active-todo" : ""}">
                    <div class="todo-item-title">
                        <input type="checkbox"${t.status ? "checked" : ""} 
                        onclick ="toggleStatus(${t.id})" 
                        />
                        <p>${t.title}</p>
                    </div>
                    <div class="option">
                        <button class="btn btn-update">
                            <img src="./img/pencil.svg" alt="icon" />
                        </button>
                        <button class="btn btn-delete" onclick="deleteTodo(${t.id})>
                            <img src="./img/remove.svg" alt="icon" />
                        </button>
                    </div>
                </div>
        `
    }
    todoListEL.innerHTML = html;
}

const deteteTodoApi = id => {
    return axios.detele(`${URL_API}/todos/${id}`);
}

const updateTodoAPI = todo => {
    return axios.put(`${URL_API}/todos/${todo.id}`, {
        titie: todo.title,
        status: todo.staus
    })
}

const toggleStatus = async (id) => {
    try {
        //Lấy ra công việc cần update trong mảng todos
        let todo = todos.find(todo => todo.id == id);

        //Thay đổi trạng thái công việc
        todo.status = !todo.status; //Thay đổi trạng thái trên sever nhưng chưa thay đổi trong mảng ban đàu

        let res = updateTodoAPI(todo);

        //Cập nhật lại mảng ban đầu
        renderTodo(todos);

    } catch (error) {
        console.log(error)
    }
}

//Lọc công việc -> Lấy ra được trạng thái của nó
//Truy cập và sử dụng vòng lặp để lắng nghe sự kiện



const deteteTodo = async (id) => {
    try {
        let isConfirm = confirm("Bạn có muốn xóa không");
        if (isConfirm) {
            //Xóa trên sever
            await deteteTodoApi(id);

            //Xóa trong mảng todos khởi tạo bên trên
            todos.forEach((todo, index) => {
                if (todo.id == id) {
                    todos.splice(index, 1);
                }
            })
            // Render lại giao diện
            renderTodo(todos);
        }
    } catch (error) {
        console.log(error);
    }
}


//Lọc công việc theo trạng thái
todoOptionItemEL.forEach(input => {
    input.addEventListener("change", function () {
        // Lấy ra value
        let status = input.value;
        getTodos(status);
    })
})

getTodos();

//Thêm công việc


const createTodoApi = title => {
    return axios.put(`${URL_API}/todo/`, {
        title: title
    })
}

//Cập nhật công việc -> Khi ấn vào ô cập nhật thì công việc sẽ bay lên ô input và cập nhật hiện lên và thêm ẩn đi

