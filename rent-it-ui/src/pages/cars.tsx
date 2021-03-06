import { useRouter } from "next/router"
import React, { FC, useCallback, useEffect, useState } from "react"
import { useUser } from "../context/Auth"
import { getUserCars, Vehicle } from "../services/api"
import styles from "../../styles/Home.module.css"
import PostAddIcon from "@mui/icons-material/PostAdd"
import Popout from "../components/Popout"

type Props = {}

const Cars: FC = (props: Props) => {
  const { user } = useUser()
  const router = useRouter()
  const [cars, setCars] = useState<Vehicle[] | null>(null)
  const [isAddPostPopOpen, setIsAddPostPopOpen] = useState<boolean>(false)

  const openAddPostDialog = useCallback(() => {
    setIsAddPostPopOpen(true)
  }, [])

  const closeAddPostDialog = useCallback(() => {
    setIsAddPostPopOpen(false)
  }, [])

  useEffect(() => {
    const getCars = async () => {
      const cars: Vehicle[] = await getUserCars(
        user!.email,
        0,
        user!.access_token
      )
      setCars(cars)
    }
    if (!user) {
      router.push("/")
    } else {
      getCars()
    }
  }, [router, user])

  if (!user) {
    return <>Loading...</>
  }
  return (
    <>
      <h2>cars</h2>
      {cars?.map((car, i) => {
        return (
          <div key={i}>
            <div className={styles.post}>
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

              <PostAddIcon
                onClick={openAddPostDialog}
                className={styles.postAdd}
                cursor="pointer"
                fontSize="large"
              />
            </div>
            {isAddPostPopOpen && (
              <Popout
                licenseNo={car.licenseNo.toString()}
                closeClick={closeAddPostDialog}
                isPopOpen={isAddPostPopOpen}
                type="AddPost"
              />
            )}
            <hr />
          </div>
        )
      })}
    </>
  )
}

export default Cars
