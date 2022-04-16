import React, { createContext, useContext, useState } from "react";
import { AuthUserSuccess } from "../services/api";

type Props = {
  children: React.ReactElement;
};

export const UserContext = createContext<AuthUserSuccess | null>(null);
export const UserUpdateContext = createContext<Function>(() => {});

export const useUser = () => {
  return {
    user: useContext(UserContext),
    userUpdate: useContext(UserUpdateContext),
  };
};

const Auth = ({ children }: Props) => {
  const [user, setUser] = useState<AuthUserSuccess | null>(null);

  const updateUser = (user: AuthUserSuccess) => {
    if (user) {
      setUser(user);
    }
  };
  return (
    <UserContext.Provider value={user}>
      <UserUpdateContext.Provider value={updateUser}>
        {children}
      </UserUpdateContext.Provider>
    </UserContext.Provider>
  );
};

export default Auth;
