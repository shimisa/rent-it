import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import styles from "../../styles/Home.module.css";
import { Button } from "@mui/material";

type Props = {};

const Register = (props: Props) => {
  const [firstName, setFirstName] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [verifiedPassword, setVerifiedPassword] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleRegister = async () => {
    const res = await fetch("http://localhost:8080/api/registration", {
      method: "POST", // *GET, POST, PUT, DELETE, etc.
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password,
        verifiedPassword: verifiedPassword,
      }),
    });
    const data = await res.json();
    console.log(data);
    if (data.status == 500) {
      console.log(data.message);
      setErrorMsg(data.message);
    }
  };

  return (
    <div className={styles.popoutForm}>
      <TextField
        value={firstName}
        onChange={(e) => {
          setFirstName(e.target.value);
        }}
        type="firstName"
        label="First Name"
        variant="standard"
      />
      <TextField
        value={lastName}
        onChange={(e) => {
          setLastName(e.target.value);
        }}
        type="lastName"
        label="Last Name"
        variant="standard"
      />
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
      <TextField
        value={verifiedPassword}
        onChange={(e) => {
          setVerifiedPassword(e.target.value);
        }}
        type="password"
        label="Verified Password"
        variant="standard"
      />
      <p>
        <small>{errorMsg}</small>
      </p>
      <Button onClick={handleRegister}>Register</Button>
    </div>
  );
};

export default Register;
