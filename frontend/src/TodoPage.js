import "./App.css";
import { Link } from "react-router-dom";

function TodoPage() {
  return (
    <div className="page-container">
      <div className="page-box">
        <div className="page-header">
          <h1 className="page-title">TODO一覧・削除画面</h1>
          <Link to="/mypage" className="header-link-button">
            マイページ画面
          </Link>
        </div>

        <div className="page-content">
          <p className="page-note">
            柚木担当画面です
          </p >
        </div>
      </div>
    </div>
  );
}

export default TodoPage;