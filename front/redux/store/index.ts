import { createSlice, configureStore } from '@reduxjs/toolkit';
import { rootReducer } from '@reduxjs/toolkit/src/tests/injectableCombineReducers.example';

const initialState = {
  theme: 'light',
};

const themeSlice = createSlice({
  name: 'theme',
  initialState,
  reducers: {
    setTheme(state, action) {
      state.theme = action.payload;
    },
  },
});

const store = configureStore({
  reducer: {
    theme: themeSlice.reducer,
  },
});

export type RootState = ReturnType<typeof rootReducer>;

export const themeActions = themeSlice.actions;

export default store;
