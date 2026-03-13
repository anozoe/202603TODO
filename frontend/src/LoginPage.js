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

  const handleLogin = (e) => {
    e.preventDefault();

    setMailError("");
    setPasswordError("");

    let valid = true;

    // メールチェック
    if (!isValidEmail(email)) {
      setMailError("メールアドレスは不正です");
      valid = false;
    }

    // パスワードチェック
    if (!password) {
      setPasswordError("パスワードは不正です");
      valid = false;
    }

    if (!valid) return;

    // 仮ログイン
    if (email === "test@test.com" && password === "abc123!@") {
      navigate("/todo");
    } else {
      setMailError("メールアドレスは不正です");
      setPasswordError("パスワードは不正です");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-box">

        <h1 className="auth-title">ログイン</h1>

        <form onSubmit={handleLogin}>

          {/* メールアドレス */}
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

          {/* パスワード */}
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

          {/* ログインボタン */}
          <button
            id="login_button"
            type="submit"
            className="main-button"
          >
            ログイン
          </button>

        </form>

        {/* ユーザ登録リンク */}
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