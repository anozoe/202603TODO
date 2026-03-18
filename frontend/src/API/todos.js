
const BASE_URL = ``;
// const TODOS_URL = 'https://zipcloud.ibsnet.co.jp/api/search?zipcode=1000001';
const TODOS_URL = `http://localhost:8080/api/todos`;

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

export const createTodo = async (todo) => {
    return fetch(`${TODOS_URL}`, { 
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify(todo),
    })
    .then(response => response.json())
    .then(data => {
        return data
    })
};

export const updateTodo = async (id, updatedTodo) => {
    return fetch(`${TODOS_URL}/${id}`, { 
        method: 'PUT',
        headers: {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify(updatedTodo),
    })
    .then(response => response.json())
    .then(data => {
        return data
    })
};
