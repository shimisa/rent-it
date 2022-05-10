import * as React from "react"
import InputUnstyled, { InputUnstyledProps } from "@mui/base/InputUnstyled"
import { styled } from "@mui/system"
import { FieldError } from "react-hook-form"

const blue = {
  100: "#DAECFF",
  200: "#80BFFF",
  400: "#3399FF",
  600: "#0072E5",
}

const grey = {
  50: "#F3F6F9",
  100: "#E7EBF0",
  200: "#E0E3E7",
  300: "#CDD2D7",
  400: "#B2BAC2",
  500: "#A0AAB4",
  600: "#6F7E8C",
  700: "#3E5060",
  800: "#2D3843",
  900: "#1A2027",
}

const StyledInputElement = styled("input")(
  ({ theme }) => `
  width: 320px;
  font-size: 0.875rem;
  font-family: IBM Plex Sans, sans-serif;
  font-weight: 400;
  line-height: 1.5;
  color: ${theme.palette.mode === "dark" ? grey[300] : grey[900]};
  background: ${theme.palette.mode === "dark" ? grey[900] : grey[50]};
  border: 1px solid ${theme.palette.mode === "dark" ? grey[800] : grey[300]};
  border-radius: 8px;
  padding: 12px 12px;

  &:hover {
    background: ${theme.palette.mode === "dark" ? "" : grey[100]};
    border-color: ${theme.palette.mode === "dark" ? grey[700] : grey[400]};
  }

  &:focus {
    outline: 3px solid ${theme.palette.mode === "dark" ? blue[600] : blue[100]};
  }
`
)

const StyledTextareaElement = styled("textarea")(
  ({ theme }) => `
  width: 320px;
  font-size: 0.875rem;
  font-family: IBM Plex Sans, sans-serif;
  font-weight: 400;
  line-height: 1.5;
  color: ${theme.palette.mode === "dark" ? grey[300] : grey[900]};
  background: ${theme.palette.mode === "dark" ? grey[900] : grey[50]};
  border: 1px solid ${theme.palette.mode === "dark" ? grey[800] : grey[300]};
  border-radius: 8px;
  padding: 12px 12px;

  &:hover {
    background: ${theme.palette.mode === "dark" ? "" : grey[100]};
    border-color: ${theme.palette.mode === "dark" ? grey[700] : grey[400]};
  }

  &:focus {
    outline: 3px solid ${theme.palette.mode === "dark" ? blue[600] : blue[100]};
  }
`
)

type Props = {
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

const CustomInput = React.forwardRef(function CustomInput(
  { register, errors, ...rest }: Props,
  ref: React.ForwardedRef<HTMLDivElement>
) {
  return (
    <InputUnstyled
      components={{
        Input: StyledInputElement,
        Textarea: StyledTextareaElement,
      }}
      {...rest}
      ref={ref}
      multiline
      maxRows={10}
      minRows={4}
      variant="outlined"
      id="description"
      label="description"
      placeholder="Description..."
      type="text"
      error={errors.description ? true : false}
      {...register("description", {
        required: {
          value: true,
          message: "Please enter a valid description.",
        },
      })}
    />
  )
})

export default function UnstyledInputBasic({ register, errors }: Props) {
  return <CustomInput register={register} errors={errors} />
}
