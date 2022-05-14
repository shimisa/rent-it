import { useRouter } from "next/router"
import React, { FC, useCallback, useEffect, useState } from "react"
import { useUser } from "../context/Auth"
import { deleteCar, getUserCars, refresh, Vehicle } from "../services/api"
import styles from "../../styles/Home.module.css"
import PostAddIcon from "@mui/icons-material/PostAdd"
import Popout from "../components/Popout"
import CancelIcon from "@mui/icons-material/Cancel"
import Tooltip from "@mui/material/Tooltip"
import useCallApi from "../components/useCallApi"

type Props = {}

const Cars: FC = (props: Props) => {
  const { user, userUpdate } = useUser()
  const router = useRouter()
  const [cars, setCars] = useState<Vehicle[] | null>(null)
  const [isAddPostPopOpen, setIsAddPostPopOpen] = useState<boolean>(false)
  const [forceUpdate, setForceUpdate] = useState<boolean>(false)

  useEffect(() => {
    if (!user) {
      router?.push("/")
    }
  }, [router, user])

  const callGetCars = useCallback(async () => {
    const res = await getUserCars(user!.email, 0, user!.access_token)
    console.log("res", res)
    if (res.error_message) {
      throw new Error("The Token has expired")
    }
    setCars(res)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [user, forceUpdate])

  useCallApi(callGetCars)

  const openAddPostDialog = useCallback(() => {
    setIsAddPostPopOpen(true)
  }, [])

  const closeAddPostDialog = useCallback(() => {
    setIsAddPostPopOpen(false)
  }, [])

  const callDeleteCar = async (vehicleId: number) => {
    await deleteCar({ vehicleId, token: user!.access_token })
    setForceUpdate((p) => !p)
  }

  // useEffect(() => {
  //   const callApi = async () => {
  //     const res = await callApiFunction() // call function from api
  //     if (res.status === 403) {
  //       const newToken = await refresh(user?.refresh_token)
  //       userUpdate(newToken)
  //     } else {
  //       //setstate(res)
  //     }
  //   }

  //   callApi()
  // }, [user?.refresh_token, userUpdate])

  return (
    <>
      {cars?.map((car, i) => {
        return (
          <div className={styles.post} key={i}>
            <div>
              <span>
                <h1> {car.description}</h1>
              </span>
              <span>
                <p>licenseNo: {car.licenseNo}</p>
                <p>Model: {car.model}</p>
                <p>Vehicle type: {car.typeOfVehicle}</p>
                <p>Engine: {car.engineType}</p>
                <p>Gear: {car.gearType}</p>
                <p>Year: {car.year}</p>
                <p>
                  Accessories:{" "}
                  {car.carAccessories.map((ac, i) => {
                    return (
                      <span key={i}>
                        {ac}
                        {" ,"}
                      </span>
                    )
                  })}
                </p>
              </span>
            </div>
            <div>
              <Tooltip title="Delete post">
                <CancelIcon onClick={() => callDeleteCar(car.id)} />
              </Tooltip>

              <Tooltip title="Add post">
                <PostAddIcon
                  onClick={openAddPostDialog}
                  cursor="pointer"
                  fontSize="large"
                />
              </Tooltip>
            </div>

            {isAddPostPopOpen && (
              <Popout
                licenseNo={car.licenseNo.toString()}
                closeClick={closeAddPostDialog}
                isPopOpen={isAddPostPopOpen}
                type="AddPost"
              />
            )}
          </div>
        )
      })}
    </>
  )
}

export default Cars
