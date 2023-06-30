import React from 'react';
import { Todo, TodoStateHandler } from '../type/todo';
import ListItem from './ListItem';

const Todolist = ({
  onComplete,
  onRemove,
  onEdit,
  onEditComplete,
  state
}: {
  onComplete: TodoStateHandler;
  onRemove: TodoStateHandler;
  onEdit: TodoStateHandler;
  onEditComplete: (id: string, text: string) => void;
  state: Todo[];
}) => {
  return (
    <ul>
      {state.map((task) => (
        <ListItem
          task={task}
          key={task.id}
          onComplete={onComplete}
          onRemove={onRemove}
          onEdit={onEdit}
          onEditComplete={onEditComplete}
        />
      ))}
    </ul>
  );
};

export default Todolist;
