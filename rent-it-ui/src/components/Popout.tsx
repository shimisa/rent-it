import React, { useCallback, useEffect, useState } from "react";
import DialogTitle from "@mui/material/DialogTitle";
import Dialog from "@mui/material/Dialog";
import Typography from "@mui/material/Typography";
import { blue } from "@mui/material/colors";
import Login from "./Login";
import Register from "./Register";

const emails = ["username@gmail.com", "user02@gmail.com"];

export interface SimpleDialogProps {
  isLoginPopOpen: boolean;
  isRegisterPopOpen: boolean;
  open: boolean;
  selectedValue: string;
  onClose: (value: string) => void;
}

function SimpleDialog(props: SimpleDialogProps) {
  const { onClose, selectedValue, open, isLoginPopOpen, isRegisterPopOpen } =
    props;

  const handleClose = useCallback(() => {
    onClose(selectedValue);
  }, [onClose, selectedValue]);

  return (
    <Dialog onClose={handleClose} open={open}>
      <DialogTitle>
        {(isLoginPopOpen && "Login") || (isRegisterPopOpen && "Register")}
        {isLoginPopOpen && <Login handleClose={handleClose} />}
        {isRegisterPopOpen && <Register  handleClose={handleClose} />}
      </DialogTitle>
    </Dialog>
  );
}

export interface PopoutProps {
  isLoginPopOpen: boolean;
  isRegisterPopOpen: boolean;
  loginCloseClick: Function;
  registerCloseClick: Function;
}

export default function Popout({
  loginCloseClick,
  registerCloseClick,
  isLoginPopOpen,
  isRegisterPopOpen,
}: PopoutProps) {
  const [open, setOpen] = useState(false);
  const [selectedValue, setSelectedValue] = useState(emails[1]);

  const handleClose = useCallback(() => {
    setOpen(false);
    loginCloseClick();
    registerCloseClick();
  }, [loginCloseClick, registerCloseClick]);

  useEffect(() => {
    const handleClickOpen = () => {
      setOpen(true);
    };

    if (isLoginPopOpen || isRegisterPopOpen) {
      handleClickOpen();
    } else {
      handleClose();
    }
  }, [handleClose, isLoginPopOpen, isRegisterPopOpen]);

  return (
    <SimpleDialog
      isLoginPopOpen={isLoginPopOpen}
      isRegisterPopOpen={isRegisterPopOpen}
      selectedValue={selectedValue}
      open={open}
      onClose={handleClose}
    />
  );
}
