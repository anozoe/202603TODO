import React, { useState } from 'react'
import '../App.css'

function PopUp() {
    const [isPopUpVisible, setPopUpVisible] = useState(false);
    const togglePopUp = () => {
        setPopUpVisible(!isPopUpVisible);
    };

  return (
    <div className='popUp'>
        <p>このTODOを本当に削除していいですか？</p>
        <button onClick={togglePopUp}>キャンセル</button>
        <button>OK</button>
    </div>
  )
}

export default PopUp