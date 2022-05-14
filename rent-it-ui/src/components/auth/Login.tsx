import React, { memo, useEffect, useState } from "react";
import TextField from "@mui/material/TextField";
import styles from "./Auth.module.css";
import { Button } from "@mui/material";
import {
  encode,
  login,
  AuthUserSuccess,
} from "../../services/api";
import { useUser } from "../../context/Auth";

type Props = {
  handleClose: Function;
};

const Login = ({ handleClose }: Props) => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [userNotFound, setuserNotFound] = useState<string>("");
  const [user, setUser] = useState<AuthUserSuccess | null>(null);
  const { userUpdate } = useUser();

  const handleLogin = async () => {
    const details: { username: string; password: string } = {
      username: email,
      password: password,
    };

    const handleLoginRes = (loginRes: any) => {
      if (loginRes.fail) {
        return setuserNotFound(loginRes.fail);
      }
      setUser(loginRes);
    };

    const loginRes = await login(encode(details));
    handleLoginRes(loginRes);
  };

  useEffect(() => {
    if (user) {
      userUpdate(user);
      handleClose();
    }
  }, [handleClose, user, userUpdate]);

  return (
    <div className={styles.authform}>
      <TextField
        value={email}
        onChange={(e) => {
          setEmail(e.target.value);
        }}
        type="email"
        label="Email"
        variant="standard"
      />
      <TextField
        value={password}
        onChange={(e) => {
          setPassword(e.target.value);
        }}
        type="password"
        label="Password"
        variant="standard"
      />
      <p>
        <small>{userNotFound}</small>{" "}
      </p>
      <Button onClick={handleLogin}>Login</Button>
    </div>
  );
};

export default memo(Login);
