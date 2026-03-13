import './App.css';
import Header from './components/Header';
import Todoitem from './components/Todoitem';
import { useState } from 'react';
import testImage from './assets/test.png';



function App() {
  //テスト用ダミーデータ
  const [todos, setTodos] = useState([
    {id: 1, image: null, title: 'タイトル1', description: '説明1'},
    {id: 2, image: null, title: 'タイトル2', description: '説明2'},
    {id: 3, image: testImage, title: 'タイトル3', description: '説明3'},
    {id: 4, image: null, title: 'タイトル2', description: '説明4'},
    {id: 5, image: null, title: 'タイトル2', description: '説明5'},
    {id: 6, image: null, title: 'タイトル2', description: '説明6'},
    {id: 7, image: null, title: 'タイトル2', description: '説明7'},
    {id: 8, image: null, title: 'タイトル2', description: '説明8'},
    {id: 9, image: null, title: 'タイトル2', description: '説明9'},
    {id: 10, image: null, title: 'タイトル2', description: '説明10'},
    {id: 11, image: null, title: 'タイトル2', description: '説明11'},
    {id: 12, image: null, title: 'タイトル2', description: '説明12'},
    {id: 13, image: null, title: 'タイトル2', description: '説明13'},
    {id: 14, image: null, title: 'タイトル2', description: '説明14'},
    {id: 15, image: null, title: 'タイトル2', description: '説明15'},
  ]);


  return (
    <div className='App'>
      <Header />
      <div className='TODO_register'>
        <button className='register_button-style'>新規登録</button>
      </div>
      <br />
      {
        todos.length === 0
        ?
        <div className='empty_message'>
          <h1>TODOがまだありません</h1>
        </div>
        :
        <div className='todo_items'>
          {todos.map(todo =>(
            <Todoitem todo={todo} key={todo.id} />
          ))}
        </div>
      }
    </div>
  );
}

export default App;
