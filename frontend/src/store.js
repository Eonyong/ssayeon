import { configureStore } from '@reduxjs/toolkit';
import authReducer from './user/auth';
import messageReducer from './user/message';


const reducer = {
  auth: authReducer,
  message: messageReducer
}

const store = configureStore({
  reducer: reducer,
  devTools: true,
})

export default store;