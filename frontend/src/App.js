import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";
import MyPage from "./MyPage";
import Header from './components/Header';
import Todoitem from './components/Todoitem';
import testImage from './assets/test.png';
import { getTodos } from './API/todos';  
import TodoList from './pages/TodoList';
import TodoNew from './pages/TodoNew';
import TodoEdit from './pages/TodoEdit';


function App() {
  // テスト用ダミーデータ
  const [todos, setTodos] = useState([]);
  useEffect(() => {
    const fetchTodos = async () => {
      const data = await getTodos();
      const mapped = data.map(todo => ({
        id: todo.id,
        title: todo.title,
        description: todo.description,
        image: todo.image,
      }));
      setTodos(mapped);
    }
    fetchTodos();
  },[]);

  const TodoPage = () => {
    return (
      <div className="App">
        <Header />

        <div className="TODO_register">
          <button 
            className="register_button-style"
          >新規登録</button>
        </div>

        <br />

        {todos.length === 0 ? (
          <div className="empty_message">
            <h1>TODOがまだありません</h1>
          </div>
        ) : (
          <div className="todo_items">
            {todos.map((todo) => (
              <Todoitem
                todo={todo}
                key={todo.id}
                todos={todos}
                setTodos={setTodos}
              />
            ))}
          </div>
        )}
      </div>
    );
  };

  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/todo" element={<TodoList />} />
      <Route path="/mypage" element={<MyPage todos={todos} setTodos={setTodos} />} />
      <Route path='/new' element={<TodoNew />} />
      <Route path='/edit' element={<TodoEdit/>} />
    </Routes>
  );
}

export default App;
