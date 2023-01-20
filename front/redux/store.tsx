import { configureStore } from '@reduxjs/toolkit';
import { setMode } from './reducers';

export const display = configureStore({ reducer: setMode });
