import '../App.css'
import React from 'react'
import { useNavigate } from 'react-router-dom'

const Header = () => {

  const navigate = useNavigate()

  const goMyPage = () => {
    navigate('/mypage')
  }

  return (
    <div className='header-style'>

      <header className='header-left'>

        <h1>TODO</h1>

        <div className='header-right'>

          <h1
            className='user_name_link'
            onClick={goMyPage}
          >
            User Name
          </h1>

          <button className='logout_button-style'>
            ログアウト
          </button>

        </div>

      </header>

    </div>
  )
}

export default Header