import React, { FC, useEffect, useState } from "react"
import { getUserOffers, updateOrderStatus } from "../services/api"
import { useUser } from "../context/Auth"
import { Button } from "@mui/material"
import { useRouter } from "next/router"

type Props = {}

type Offer = {
  orderId: number
  orderRequestDate: string
  valid: boolean
  orderStatus: "PENDING" | "COFIRMED" | "DECLINED" | "CANCELED"
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
  const [forceRender, setForceRender] = useState<boolean>(true)
  const [offers, setoffers] = useState<Offer[]>()
  const { user } = useUser()
  const router = useRouter()

  useEffect(() => {
    if (!user) {
      router?.push("/")
    }
  }, [router, user])

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
  }, [user, forceRender])

  const callDeclineOffer = async (orderId: number) => {
    const res = await updateOrderStatus({
      status: "DECLINED",
      orderId,
      token: user!.access_token,
    })
    if (res === 200) {
      setForceRender((p) => !p)
    }
  }

  return (
    <div>
      {offers?.map((offer) => {
        return (
          <>
            <b>Order ID: {offer.orderId}</b>
            <p>Vehicle LicenseNo: {offer.vehicleLicenseNo}</p>
            <p>
              Requested Date:{" "}
              {new Date(offer.orderRequestDate).toLocaleDateString()}
            </p>
            <p>The Order is {offer.orderStatus}</p>
            {offer.orderStatus === "PENDING" && (
              <Button onClick={() => callDeclineOffer(offer.orderId)}>
                Decline
              </Button>
            )}
            <hr />
          </>
        )
      })}
    </div>
  )
}

export default MyOffers
