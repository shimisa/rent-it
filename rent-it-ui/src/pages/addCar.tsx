import { Container, Grid, TextField } from "@material-ui/core"
import {
  Button,
  ButtonGroup,
  ToggleButton,
  ToggleButtonGroup,
  Typography,
} from "@mui/material"
import React, { FC, useState } from "react"
import { EventType, SubmitHandler, useForm } from "react-hook-form"
import IndexPage from "../pages/index"
import { useRouter } from "next/router"
import { addNewCar } from "../services/api"
import { useUser } from "../context/Auth"


interface IFormInput {
  lisanceNo: string
  typeOfVehicle: string
  model: string
  year: string
  gearType: string
  engineType: string
  description: string
  carAccessories: CarAccessories
}

export type CarAccessories = []

const AddCar: FC = () => {
  const { user } = useUser()

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm<IFormInput>()
  const [typeOfVehicle, setTypeOfVehicle] = useState<string>("")
  const [gearType, setGearType] = useState<string>("")
  const [engineType, setEngineType] = useState<string>("")
  const [carAccessories, setCarAccessories] = useState<[]>(() => [])

  const onSubmit: SubmitHandler<IFormInput> = async ({
    lisanceNo,
    model,
    year,
    description,
  }) => {
    try {
      // addNewCar
      const res = await addNewCar(
        {
          licenseNo: lisanceNo,
          typeOfVehicle,
          model,
          year,
          gearType,
          engineType,
          description,
          carAccessories,
          ownerUserName: user?.email,
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

  const handleVehicleChange = (e: React.SyntheticEvent<EventTarget>) => {
    const s = (e.target as HTMLInputElement).value.toString()
    setTypeOfVehicle(s)
  }
  const handleGearChange = (e: React.SyntheticEvent<EventTarget>) => {
    const s = (e.target as HTMLInputElement).value.toString()
    setGearType(s)
  }
  const handleEngineChange = (e: React.SyntheticEvent<EventTarget>) => {
    const s = (e.target as HTMLInputElement).value.toString()
    setEngineType(s)
  }
  const handleCarAccessoriesChange = (
    e: React.SyntheticEvent<EventTarget>,
    newAccessories: []
  ) => {
    setCarAccessories(newAccessories)
  }
  console.log(errors)

  if (!user) {
    return null
  }

  return (
    <Container maxWidth="md">
      <form onSubmit={handleSubmit(onSubmit)} autoComplete="off">
        <Grid container direction="column" spacing={3}>
          <Grid item>
            <Typography>Add Car</Typography>
          </Grid>
          <Grid item>
            <TextField
              fullWidth
              variant="outlined"
              id="lisanceNo"
              label="lisanceNo"
              type="text"
              error={errors.lisanceNo ? true : false}
              helperText={errors.lisanceNo ? errors.lisanceNo.message : null}
              {...register("lisanceNo", {
                required: {
                  value: true,
                  message: "Please enter a valid lisanceNo.",
                },
              })}
            />
          </Grid>
          <Grid item>
            <Typography>Type of Vehicle</Typography>
            <ToggleButtonGroup
              value={typeOfVehicle}
              id="typeOfVehicle"
              {...register("typeOfVehicle")}
              onChange={handleVehicleChange}
            >
              <ToggleButton value="CAR">CAR</ToggleButton>
              <ToggleButton value="SUV">SUV</ToggleButton>
              <ToggleButton value="TRUCK">TRUCK</ToggleButton>
              <ToggleButton value="VAN">VAN</ToggleButton>
              <ToggleButton value="CAR_4WD">CAR_4WD</ToggleButton>
              <ToggleButton value="MOTORCYCLE">MOTORCYCLE</ToggleButton>
              <ToggleButton value="SCOOTER">SCOOTER</ToggleButton>
            </ToggleButtonGroup>
          </Grid>
          <Grid item>
            <TextField
              fullWidth
              variant="outlined"
              id="model"
              label="model"
              type="text"
              error={errors.model ? true : false}
              helperText={errors.model ? errors.model.message : null}
              {...register("model", {
                required: {
                  value: true,
                  message: "Please enter a valid model.",
                },
              })}
            />
          </Grid>
          <Grid item>
            <TextField
              fullWidth
              variant="outlined"
              id="year"
              label="year"
              type="text"
              error={errors.year ? true : false}
              helperText={errors.year ? errors.year.message : null}
              {...register("year", {
                required: {
                  value: true,
                  message: "Please enter a valid year.",
                },
              })}
            />
          </Grid>
          <Grid item>
            <Typography>Gear Type</Typography>
            <ToggleButtonGroup
              value={gearType}
              id="gearType"
              {...register("gearType")}
              onChange={handleGearChange}
            >
              <ToggleButton value="MANUAL">MANUAL</ToggleButton>
              <ToggleButton value="AUTOMATIC">AUTOMATIC</ToggleButton>
              <ToggleButton value="ROBOTIC">ROBOTIC</ToggleButton>
            </ToggleButtonGroup>
          </Grid>
          <Grid item>
            <Typography>Engine Type</Typography>
            <ToggleButtonGroup
              id="engineType"
              {...register("engineType")}
              value={engineType}
              onChange={handleEngineChange}
            >
              <ToggleButton value="GASOLINE">GASOLINE</ToggleButton>
              <ToggleButton value="DIESEL">DIESEL</ToggleButton>
              <ToggleButton value="ELECTRIC">ELECTRIC</ToggleButton>
            </ToggleButtonGroup>
          </Grid>
          <Grid item>
            <TextField
              fullWidth
              variant="outlined"
              id="description"
              label="description"
              type="text"
              error={errors.description ? true : false}
              helperText={
                errors.description ? errors.description.message : null
              }
              {...register("description", {
                required: {
                  value: true,
                  message: "Is there description?",
                },
              })}
            />
          </Grid>
          <Grid item>
            <Typography>Car Accessories</Typography>
            <ToggleButtonGroup
              exclusive={false}
              value={carAccessories}
              id="carAccessories"
              {...register("carAccessories")}
              onChange={handleCarAccessoriesChange}
            >
              <ToggleButton value="AIR_CONDITION"> Air Condition</ToggleButton>
              <ToggleButton value="ABS">ABS</ToggleButton>
              <ToggleButton value="ELECTRIC_WINDOWS">
                ELECTRIC WINDOWS
              </ToggleButton>
              <ToggleButton value="ROOF_WINDOW">Roof window</ToggleButton>
              <ToggleButton value="MAGNESIUM_WHEELS">
                MAGNESIUM WHEELS
              </ToggleButton>
              <ToggleButton value="TIRE_PRESSURE_SENSORS">
                TIRE PRESSURE SENSORS
              </ToggleButton>
            </ToggleButtonGroup>
          </Grid>

          <Grid item>
            <Button type="submit">Add</Button>
          </Grid>
        </Grid>
      </form>
    </Container>
  )
}

export default AddCar
