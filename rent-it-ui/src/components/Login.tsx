import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import styles from "../../styles/Home.module.css";
import { Button } from "@mui/material";

type Props = {};

const Login = (props: Props) => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [userNotFound, setuserNotFound] = useState<string>("");

  const handleLogin = async () => {
    var details = {
      username: email,
      password: password,
    };

    var formBody = [];
    let property: keyof typeof details;
    for (property in details) {
      var encodedKey = encodeURIComponent(property);
      var encodedValue = encodeURIComponent(details[property]);
      formBody.push(encodedKey + "=" + encodedValue);
    }
    var strFormBody = formBody.join("&");

    try {
      const res = await fetch("http://localhost:8080/api/login", {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        headers: {
          // "Content-Type": "application/json",
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded",
        },
        body: strFormBody,
      });
      const data = await res.json();
      console.log(data);
    } catch (err: any) {
      setuserNotFound("Incorect user name or password")
    }
  };

  return (
    <div className={styles.popoutForm}>
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
      <p><small>{userNotFound}</small> </p>
      <Button onClick={handleLogin}>Login</Button>
    </div>
  );
};

export default Login;
