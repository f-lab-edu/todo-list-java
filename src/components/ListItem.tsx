import React, { useState } from 'react';
import { Todo, TodoStateHandler } from '../type/todo';

const ListItem = ({
  onComplete,
  onEdit,
  onRemove,
  onEditComplete,
  task
}: {
  onComplete: TodoStateHandler;
  onEdit: TodoStateHandler;
  onRemove: TodoStateHandler;
  onEditComplete: (id: string, editText: string) => void;
  task: Todo;
}) => {
  const [editText, setEditText] = useState('');

  const changeEditText = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEditText(e.target.value);
  };

  const editInputText = () => {
    onEditComplete(task.id, editText);
    setEditText('');
  };

  const CompleteButtonText = task.isCompleted ? '완료취소' : '완료';
  const listItem = task.isEdit ? (
    <>
      <input
        type='text'
        value={editText}
        onChange={changeEditText}
        placeholder='수정할 값을 입력해 주세요.'
      ></input>
      <button onClick={editInputText}>수정완료</button>
    </>
  ) : (
    <>
      {(task.isCompleted ? '[완료] ' : '') + task.text}
      <button onClick={() => onComplete(task.id)}>{CompleteButtonText}</button>
      <button onClick={() => onEdit(task.id)}>수정</button>
    </>
  );
  return (
    <div>
      <li key={task.id}>
        {listItem}
        <button onClick={() => onRemove(task.id)}>삭제</button>
      </li>
    </div>
  );
};

export default ListItem;
