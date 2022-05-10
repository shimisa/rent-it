import { Button, Container, Grid, TextField, Typography } from "@mui/material"
import React, { useCallback } from "react"
import { SubmitHandler, useForm } from "react-hook-form"
import { useUser } from "../context/Auth"
import { addNewPost } from "../services/api"
import TextArea from "../components/TextArea"
import DatePicker from "../components/DatePicker"

type Props = {
  handleClose: Function
  licenseNo: string
}
interface IFormInput {
  header: string
  description: string
  fromDate: string
  tillDate: string
  licenseNo: string
}

const AddPostForm = ({ licenseNo, handleClose }: Props) => {
  const { user } = useUser()
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm<IFormInput>()

  const [fromDate, setFrom] = React.useState<Date | null>(null)
  const [tillDate, setTo] = React.useState<Date | null>(null)

  const setFromValue = useCallback((value: Date) => {
    setFrom(value)
  }, [])
  const setToValue = useCallback((value: Date) => {
    setTo(value)
  }, [])

  const onSubmit: SubmitHandler<IFormInput> = async ({
    header,
    description,
  }) => {
    console.log(header, description, fromDate, tillDate, licenseNo)

    try {
      // addNewCar
      const res = await addNewPost(
        {
          header,
          description,
          fromDate,
          tillDate,
          licenseNo,
        },
        user!.access_token
      )
      alert(res.status)
      console.log("New post created successfully")
      // router.push(`/offers/${createNewPost.data.createPost!.id}`)
    } catch (error) {
      console.log("Error Adding Car: ", error)
    }
  }

  if (!user) {
    return null
  }
  console.log(errors)

  return (
    <Container maxWidth="md">
      <form onSubmit={handleSubmit(onSubmit)} autoComplete="off">
        <Grid container direction="column" spacing={3}>
          <Grid item>
            <h2>Add Post</h2>
          </Grid>
          <Grid item>
            <TextField
              fullWidth
              variant="outlined"
              id="header"
              label="header"
              type="text"
              error={errors.header ? true : false}
              helperText={errors.header ? errors.header.message : null}
              {...register("header", {
                required: {
                  value: true,
                  message: "Please enter a valid header.",
                },
              })}
            />
          </Grid>
          <Grid item>
            <TextArea errors={errors} register={register} />
          </Grid>
          <Grid item>
            <DatePicker
              setFromValue={setFromValue}
              setToValue={setToValue}
              errors={errors}
              register={register}
            />
          </Grid>
        </Grid>
        <Button type="submit" variant="outlined">
          Post
        </Button>
      </form>
    </Container>
  )
}

export default AddPostForm
