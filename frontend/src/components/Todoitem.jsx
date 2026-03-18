import '../App.css'
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Todoitem = ({ todo }) => {
    const [isPopUpVisible, setPopUpVisible] = useState(false);
    const togglePopUp = () => {
        setPopUpVisible(!isPopUpVisible);
    };
    const navigate = useNavigate();
  return (
    <div>
      <div className='todo_item'>
        <div className='image'>
          {todo.image
          ?
          <img src={todo.image} alt={todo.title} />
          :
          <div className='no-image'>No Image</div>
          }
        </div>
        <p>{todo.title}</p>
        <p>{todo.description}</p>
        <div className='todo_btn'>
          <button 
            className='btn_edit'
            onClick={() => navigate('/edit', {state: todo})}
          >
            編集
          </button>
          <button onClick={togglePopUp}
                  className='btn_delete'
          >
            削除
          </button>
        </div>
      </div>
      {isPopUpVisible && (
        <div className='popUp'>
          <p>このTODOを本当に削除していいですか？</p>
          <div className='btn_PopUp'>
            <button onClick={togglePopUp}
                    className='btn_cancel'
            >
              キャンセル
            </button>
            <button className='btn_ok'>OK</button>
          </div>
        </div>
      )}
    </div>
  )
}

export default Todoitem