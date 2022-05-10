import React, { useEffect } from "react"
import TextField from "@mui/material/TextField"
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns"
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider"
import { DatePicker } from "@mui/x-date-pickers/DatePicker"
import styles from "../../styles/Home.module.css"
import { FieldError } from "react-hook-form"

type Props = {
  setFromValue: Function
  setToValue: Function
  //   inputProps: InputUnstyledProps
  register: Function
  errors: {
    header?: FieldError | undefined
    description?: FieldError | undefined
    fromDate?: FieldError | undefined
    tillDate?: FieldError | undefined
    licenseNo?: FieldError | undefined
  }
}

export default function BasicDatePicker({
  register,
  errors,
  setFromValue,
  setToValue,
}: Props) {
  const [from, setFrom] = React.useState<Date | null>(null)
  const [to, setTo] = React.useState<Date | null>(null)

  useEffect(() => {
    if (to && from) {
      if (to <= from) {
        setFrom(to)
      } else {
        setFromValue(from)
        setToValue(to)
      }
    }
  }, [from, setFromValue, setToValue, to])

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns}>
      <div className={styles.DatePicker}>
        <DatePicker
          label="From"
          value={from}
          renderInput={(params) => <TextField {...params} />}
          // error={errors.fromDate ? true : false}
          // {...register("fromDate", {
          //   required: {
          //     value: true,
          //     message: "Please enter a valid fromDate.",
          //   },
          // })}
          onChange={(newValue) => {
            setFrom(newValue)
          }}
        />
        <DatePicker
          label="To"
          value={to}
          // error={errors.tillDate ? true : false}
          // {...register("tillDate", {
          //   required: {
          //     value: true,
          //     message: "Please enter a valid tillDate.",
          //   },
          // })}
          onChange={(newValue) => {
            setTo(newValue)
          }}
          renderInput={(params) => <TextField {...params} />}
        />
      </div>
    </LocalizationProvider>
  )
}
