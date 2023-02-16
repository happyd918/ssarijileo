import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  sessionId: '',
  token: '',
};

const sessionIdSlice = createSlice({
  name: 'sessionId',
  initialState,
  reducers: {
    setSessionId(state, action) {
      state.sessionId = action.payload;
    },
    setToken(state, action) {
      state.token = action.payload;
    },
  },
});

export const { setSessionId, setToken } = sessionIdSlice.actions;

export default sessionIdSlice;
