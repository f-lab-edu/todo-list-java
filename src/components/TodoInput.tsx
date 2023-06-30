import React, { useState } from 'react';

const TodoInput = ({
  onSubmit
}: {
  onSubmit: (e: React.FormEvent<HTMLFormElement>, inputText: string) => void;
}) => {
  const [inputText, setInputText] = useState('');

  const changeInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputText(e.target.value);
  };

  const submitInput = (e: React.FormEvent<HTMLFormElement>) => {
    onSubmit(e, inputText);
    setInputText('');
  };

  return (
    <div>
      <form onSubmit={submitInput}>
        <input
          type='text'
          value={inputText}
          onChange={changeInput}
          placeholder='할 일을 입력해 주세요.'
        />
        <button>추가</button>
      </form>
    </div>
  );
};

export default TodoInput;
