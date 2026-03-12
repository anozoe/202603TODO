import '../App.css'

import React from 'react'

const Header = () => {
  return (
    <div className='header-style'>
        <header className='header-left'>
          <h1>TODO</h1>
          <div className='header-right'>
            <h1>User Name</h1>
            <button className='logout_button-style'>ログアウト</button>
          </div>
        </header>
    </div>
  )
}

export default Header