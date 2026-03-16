import "./App.css";
import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

function LoginPage() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [mailError, setMailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const isValidEmail = (value) => {
    const regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    return regex.test(value);
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    setMailError("");
    setPasswordError("");

    let valid = true;

    if (!isValidEmail(email)) {
      setMailError("メールアドレスは不正です");
      valid = false;
    }

    if (!password) {
      setPasswordError("パスワードは不正です");
      valid = false;
    }

    if (!valid) return;

    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          email: email,
          password: password
        })
      });

      if (!response.ok) {
        setMailError("メールアドレスまたはパスワードが違います");
        setPasswordError("メールアドレスまたはパスワードが違います");
        return;
      }

      const data = await response.json();

      localStorage.setItem("loginUserId", data.id);
      localStorage.setItem("loginUserName", data.userName);
      localStorage.setItem("loginUserEmail", data.email);

      console.log("ログイン成功:", data);

      navigate("/todo");
    } catch (error) {
      console.error(error);
      setMailError("サーバーに接続できません");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-box">
        <h1 className="auth-title">ログイン</h1>

        <form onSubmit={handleLogin}>
          <div className="input-group">
            <label htmlFor="email">メールアドレス</label>

            <input
              id="email"
              type="text"
              maxLength="50"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="メールアドレスを入力"
            />

            {mailError && (
              <p id="mail_error_message" className="error-text">
                {mailError}
              </p >
            )}
          </div>

          <div className="input-group">
            <label htmlFor="password">パスワード</label>

            <input
              id="password"
              type="password"
              maxLength="50"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="パスワードを入力"
            />

            {passwordError && (
              <p id="password_error_message" className="error-text">
                {passwordError}
              </p >
            )}
          </div>

          <button
            id="login_button"
            type="submit"
            className="main-button"
          >
            ログイン
          </button>
        </form>

        <div className="link-area">
          <Link
            id="to_register_link"
            to="/register"
            className="sub-link"
          >
            新規会員登録はこちら
          </Link>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;