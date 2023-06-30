import React, { useEffect, useReducer } from 'react';
import '../css/App.css';
import TodoInput from './TodoInput';
import Todolist from './TodoList';
import { reducer } from '../reducer';
import shortid from 'shortid';
import axios from 'axios';

const App = () => {
  const [state, dispatch] = useReducer(reducer, []);

  useEffect(() => {
    getTodos();
  }, []);

  const getTodos = async () => {
    try {
      const response = await axios.get('/api');
      dispatch({ type: 'SUCCESS', data: response.data });
    } catch (e) {
      console.error(e);
    }
  };

  const handleSubmitButtonClick = (
    e: React.FormEvent<HTMLFormElement>,
    inputText: string
  ) => {
    e.preventDefault();
    const dataId = shortid.generate();
    dispatch({ type: 'SUBMIT', id: dataId, text: inputText });
  };

  const handleCompleteButtonClick = (id: string) => {
    dispatch({ type: 'COMPLETE', id });
  };

  const handleEditButtonClick = (id: string) => {
    dispatch({ type: 'EDIT', id });
  };

  const handleEditCompleteButtonClick = (id: string, editText: string) => {
    dispatch({ type: 'EDITCOMPLETE', id, text: editText });
  };

  const handleRemoveButtonClick = (id: string) => {
    dispatch({ type: 'REMOVE', id });
  };

  return (
    <div className='App'>
      <h1>To-do</h1>
      <TodoInput onSubmit={handleSubmitButtonClick} />
      <Todolist
        onComplete={handleCompleteButtonClick}
        onRemove={handleRemoveButtonClick}
        onEdit={handleEditButtonClick}
        onEditComplete={handleEditCompleteButtonClick}
        state={state}
      />
    </div>
  );
};

export default App;
