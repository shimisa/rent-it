import React, { FC, useEffect, useState } from "react"
import { getUserOrders } from "../services/api"
import { useUser } from "../context/Auth"

type Props = {}

type Order = {
  orderId: number
  orderRequestDate: string
  valid: boolean
  confirmed: boolean
  declined: boolean
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
  const { user } = useUser()
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
  }, [user])

  return (
    <div>
      {orders?.map((order) => {
        return (
          <>
            <b>Order ID: {order.orderId}</b>
            <p>Requested Date: {new Date(order.orderRequestDate).toLocaleDateString()}</p>
            <p>The Order is {order.confirmed ? "confirmed" : "not confirmed"}</p>
            <hr/>
          </>
        )
      })}
    </div>
  )
}

export default MyOrders
