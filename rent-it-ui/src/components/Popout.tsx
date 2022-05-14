import React, { useCallback, useEffect, useState } from "react"
import DialogTitle from "@mui/material/DialogTitle"
import Dialog from "@mui/material/Dialog"
import Typography from "@mui/material/Typography"
import Login from "./auth/Login"
import Register from "./auth/Register"
import { DialogContent } from "@mui/material"
import AddPostForm from "./Posts/AddPostForm"

const emails = ["username@gmail.com", "user02@gmail.com"]

export interface SimpleDialogProps {
  licenseNo?: string
  isPopOpen: boolean
  open: boolean
  selectedValue: string
  onClose: (value: string) => void
  type: string
}

function SimpleDialog(props: SimpleDialogProps) {
  const { onClose, selectedValue, open, licenseNo, type } = props

  const handleClose = useCallback(() => {
    onClose(selectedValue)
  }, [onClose, selectedValue])

  return (
    <Dialog onClose={handleClose} open={open}>
      <DialogTitle>
        {(type === "Login" && "Login") || (type === "Register" && "Register")}
      </DialogTitle>
      <DialogContent>
        {type === "Login" && <Login handleClose={handleClose} />}
        {type === "Register" && <Register handleClose={handleClose} />}
        {type === "AddPost" && licenseNo && (
          <AddPostForm licenseNo={licenseNo} handleClose={handleClose} />
        )}
      </DialogContent>
    </Dialog>
  )
}

export interface PopoutProps {
  licenseNo?: string
  isPopOpen: boolean
  closeClick: Function
  type: string
}

export default function Popout({
  closeClick,
  isPopOpen,
  type,
  licenseNo,
}: PopoutProps) {
  const [open, setOpen] = useState(false)
  const [selectedValue, setSelectedValue] = useState(emails[1])

  const handleClose = useCallback(() => {
    setOpen(false)
    closeClick()
  }, [closeClick])

  useEffect(() => {
    const handleClickOpen = () => {
      setOpen(true)
    }

    if (isPopOpen) {
      handleClickOpen()
    } else {
      handleClose()
    }
  }, [handleClose, isPopOpen])

  return (
    <SimpleDialog
      licenseNo={licenseNo}
      type={type}
      isPopOpen={isPopOpen}
      selectedValue={selectedValue}
      open={open}
      onClose={handleClose}
    />
  )
}
