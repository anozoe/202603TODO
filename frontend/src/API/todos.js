const BASE_URL = ``;
// const TODOS_URL = 'https://zipcloud.ibsnet.co.jp/api/search?zipcode=1000001';
const TODOS_URL = `http://localhost:8080/todos`;

export const getTodos = async () => {
    return fetch(TODOS_URL)
        .then(response => response.json())
        .then(data => {
            return data
        })
};

export const getTodosByUser = async (id) => {
    return fetch(`${TODOS_URL}/${id}`)
      .then(response => response.json())
      .then(data => {
          return data
      })
};

export const deleteTodo = async (id) => {
    return fetch(`${TODOS_URL}/${id}`, { method: 'DELETE' })
      .then(response => response.json())
      .then(data => {
          return data
      })
};

