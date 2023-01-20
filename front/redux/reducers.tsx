import { IS_DARK } from './actions';

const initialState = false;
export function setMode(
  previousState = initialState,
  action: { type: boolean; mode: boolean },
) {
  // 초기값 세팅
  // if (previousState === undefined) {
  //     return [];
  // }
  if (action.type === IS_DARK) {
    return action.mode;
  }

  return previousState;
}
