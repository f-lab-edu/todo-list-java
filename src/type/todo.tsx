export interface Todo {
  id: string;
  text: string;
  isCompleted: boolean;
  isEdit: boolean;
}

export type TodoStateHandler = (id: string) => void;
