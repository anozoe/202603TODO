import "./App.css";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function MyPage() {
  const navigate = useNavigate();

  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");

  const [editingUserName, setEditingUserName] = useState(false);
  const [editingEmail, setEditingEmail] = useState(false);

  const [userNameInput, setUserNameInput] = useState("");
  const [emailInput, setEmailInput] = useState("");

  const [todos, setTodos] = useState([
    { id: 1, title: "title_1", description: "text" },
    { id: 2, title: "title_2", description: "text" },
    { id: 3, title: "title_3", description: "text" }
  ]);

  useEffect(() => {
    const loginUserId = localStorage.getItem("loginUserId");

    if (!loginUserId) {
      alert("ログイン情報がありません");
      navigate("/");
      return;
    }

    fetch(`http://localhost:8080/api/users/${loginUserId}`)
      .then((res) => {
        if (!res.ok) {
          throw new Error("ユーザー情報の取得に失敗しました");
        }
        return res.json();
      })
      .then((data) => {
        setUserName(data.user_name);
        setEmail(data.email);
        setUserNameInput(data.user_name);
        setEmailInput(data.email);
      })
      .catch((error) => {
        console.error(error);
        alert("ユーザー情報の取得に失敗しました");
      });
  }, [navigate]);

  const handleUserNameEdit = () => {
    setEditingUserName(true);
  };

  const handleEmailEdit = () => {
    setEditingEmail(true);
  };

  const handleUserNameUpdate = () => {
    setUserName(userNameInput);
    setEditingUserName(false);
  };

  const handleEmailUpdate = () => {
    setEmail(emailInput);
    setEditingEmail(false);
  };

  const handleTodoEdit = () => {
    navigate("/todo");
  };

  const deleteTodo = (id) => {
    const newTodos = todos.filter((todo) => todo.id !== id);
    setTodos(newTodos);
  };

  return (
    <div className="page-container">
      <div className="mypage-box">
        <h1 className="page-title">マイページ</h1>

        <div className="todo-top-button-row">
          <button
            className="register_button-style todo-top-button"
            onClick={() => navigate("/todo")}
          >
            TODO一覧
          </button>
        </div>

        <div className="profile-card">
          <div className="profile-row">
            {editingUserName ? (
              <input
                className="profile-input"
                value={userNameInput}
                onChange={(e) => setUserNameInput(e.target.value)}
              />
            ) : (
              <div className="profile-text">User Name : {userName}</div>
            )}

            <div className="profile-buttons">
              {!editingUserName && (
                <button className="btn_edit" onClick={handleUserNameEdit}>
                  編集
                </button>
              )}

              {editingUserName && (
                <button className="update-button" onClick={handleUserNameUpdate}>
                  更新
                </button>
              )}
            </div>
          </div>
        </div>

        <div className="profile-card">
          <div className="profile-row">
            {editingEmail ? (
              <input
                className="profile-input"
                value={emailInput}
                onChange={(e) => setEmailInput(e.target.value)}
              />
            ) : (
              <div className="profile-text">Email : {email}</div>
            )}

            <div className="profile-buttons">
              {!editingEmail && (
                <button className="btn_edit" onClick={handleEmailEdit}>
                  編集
                </button>
              )}

              {editingEmail && (
                <button className="update-button" onClick={handleEmailUpdate}>
                  更新
                </button>
              )}
            </div>
          </div>
        </div>

        <div className="task-list">
          {todos.map((todo) => (
            <div key={todo.id} className="task-card">
              <div className="task-left">
                <div className="task-title">{todo.title.toUpperCase()}</div>
                <div className="task-meta">{todo.description}</div>
              </div>

              <div className="task-actions">
                <button className="btn_edit" onClick={handleTodoEdit}>
                  編集
                </button>

                <button
                  className="btn_delete"
                  onClick={() => deleteTodo(todo.id)}
                >
                  削除
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default MyPage;