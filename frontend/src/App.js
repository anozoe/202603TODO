import { Routes, Route } from 'react-router-dom';
import TodoList from './pages/TodoList';
import TodoEditNew from './pages/TodoEditNew';

function App() {
  return (
    <Routes>
      <Route path='/' element={<TodoList />} />
      <Route path='/new' element={<TodoEditNew />} />
    </Routes>
  )
}

export default App