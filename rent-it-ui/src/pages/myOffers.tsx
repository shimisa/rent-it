import React, { FC, useEffect, useState } from "react"
import { getUserOffers } from "../services/api"
import { useUser } from "../context/Auth"

type Props = {}

type Offer = {
  orderId: number
  orderRequestDate: string
  valid: boolean
  confirmed: boolean
  declined: boolean
  vehicleLicenseNo: number
  post: {
    postId: number
    postedAt: string
    header: string
    description: string
    fromDate: string
    tillDate: string
    typeOfVehicle: string
    model: string
    year: number
    gearType: string
    engineType: string
    carDescription: string
    carAccessories: string[]
    rentalFirstName: string
    ratingOfRental: string
  }
}

const MyOffers: FC = (props: Props) => {
  const [offers, setoffers] = useState<Offer[]>()
  const { user } = useUser()
  useEffect(() => {
    const callGetUserOffers = async () => {
      if (user) {
        try {
          const res = await getUserOffers({
            email: user!.email,
            token: user!.access_token,
          })
          console.log(res)
          setoffers(res)
        } catch (e) {
          console.log(e)
        }
      }
    }
    callGetUserOffers()
  }, [user])

  return (
    <div>
      {offers?.map((offer) => {
        return (
          <>
            <b>Order ID: {offer.orderId}</b>
            <p>Vehicle LicenseNo: {offer.vehicleLicenseNo}</p>
            <p>Requested Date: {new Date(offer.orderRequestDate).toLocaleDateString()}</p>
            <p>The Order is {offer.confirmed ? "confirmed" : "not confirmed"}</p>
            <hr/>
          </>
        )
      })}
    </div>
  )
}

export default MyOffers
