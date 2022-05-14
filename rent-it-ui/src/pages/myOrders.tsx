import React, { FC, useEffect, useState } from "react"
import { getUserOrders, updateOrderStatus } from "../services/api"
import { useUser } from "../context/Auth"
import { Button } from "@mui/material"
import { useRouter } from "next/router"

type Props = {}

type Order = {
  orderId: number
  orderRequestDate: string
  valid: boolean
  orderStatus: "PENDING" | "COFIRMED" | "DECLINED" | "CANCELED"
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

const MyOrders: FC = (props: Props) => {
  const [orders, setOrders] = useState<Order[]>()
  const [forceRender, setForceRender] = useState<boolean>(true)
  const { user } = useUser()
  const router = useRouter()

  useEffect(() => {
    const callGetUserOrders = async () => {
      if (user) {
        try {
          const res = await getUserOrders({
            email: user!.email,
            token: user!.access_token,
          })
          setOrders(res)
        } catch (e) {
          console.log(e)
        }
      }
    }
    callGetUserOrders()
  }, [user, forceRender])

  useEffect(() => {
    if (!user) {
      router?.push("/")
    }
  }, [router, user])

  const callCancelOrder = async (orderId: number) => {
    const res = await updateOrderStatus({
      status: "CANCELED",
      orderId,
      token: user!.access_token,
    })
    if (res === 200) {
      setForceRender((p) => !p)
    }
  }

  return (
    <div>
      {orders?.map((order) => {
        return (
          <>
            <b>Order ID: {order.orderId}</b>
            <p>
              Requested Date:{" "}
              {new Date(order.orderRequestDate).toLocaleDateString()}
            </p>
            <p>The Order is {order.orderStatus}</p>
            {order.orderStatus === "PENDING" && (
              <Button onClick={() => callCancelOrder(order.orderId)}>
                CANCEL
              </Button>
            )}
            <hr />
          </>
        )
      })}
    </div>
  )
}

export default MyOrders
