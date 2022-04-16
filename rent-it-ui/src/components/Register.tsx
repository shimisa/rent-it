import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import styles from "../../styles/Home.module.css";
import { Button } from "@mui/material";
import { register, registerConfirm } from "../services/api";

type Props = {
  handleClose: Function;
};

const Register = ({ handleClose }: Props) => {
  const [firstName, setFirstName] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [verifiedPassword, setVerifiedPassword] = useState<string>("");
  const [token, setToken] = useState<string>("");
  const [msg, setMsg] = useState<string>("");

  const handleConfirmation = async () => {
    const res = await registerConfirm(token)
    if (res.status == 500) {
      const data = await res.json();

      console.log(data.message);
      setMsg(data.message);
    }
    if (res.status == 200) {
      setMsg("Confirmed");
      handleClose();
    }
  };

  const handleRegister = async () => {
    const res = await register({
      firstName,
      lastName,
      email,
      password,
      verifiedPassword,
    });
    if (res.status == 500) {
      const data = await res.json();

      console.log(data.message);
      setMsg(data.message);
    }
    if (res.status == 201) {
      setMsg("Email confirmation sent");
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
        <small>{msg}</small>
      </p>
      {msg === "Email confirmation sent" && (
        <>
          <TextField
            value={token}
            onChange={(e) => {
              setToken(e.target.value);
            }}
            type="text"
            label="Token"
            variant="standard"
          />
          <Button onClick={handleConfirmation}>Confirm email</Button>
        </>
      )}
      <Button onClick={handleRegister}>Register</Button>
    </div>
  );
};

export default Register;
