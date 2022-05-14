import React from "react"
import { addOrder, Post } from "../../services/api"
import styles from "../../../styles/Home.module.css"
import { useUser } from "../../context/Auth"
import CarRentalOutlinedIcon from "@mui/icons-material/CarRentalOutlined"
import { Typography } from "@mui/material"
import Tooltip from "@mui/material/Tooltip"

type Props = {
  post: Post
}

const Post = ({ post }: Props) => {
  const { user } = useUser()
  const placeOrder = async () => {
    await addOrder(
      {
        orderedByEmail: user!.email,
        postId: post.postId,
      },
      user!.access_token
    )
  }

  return (
    <div className={styles.post} key={post.postId}>
      <div>
        <Typography variant="h4">{post.header}</Typography>
        <p> {post.description}</p>
        <div>
          <p>Model: {post.model}</p>
          <p>Vehicle type: {post.typeOfVehicle}</p>
          <p>Engine: {post.engineType}</p>
          <p>Gear: {post.gearType}</p>
          <p>Year: {post.year}</p>
          <p>
            Accessories:{" "}
            {post.carAccessories.map((ac, i) => {
              return (
                <span key={i}>
                  {ac}
                  {" ,"}
                </span>
              )
            })}
          </p>
          <p>
            Available from {new Date(post.fromDate).toLocaleDateString()} To{" "}
            {new Date(post.tillDate).toLocaleDateString()}
          </p>
        </div>
      </div>

      {user ? (
        <div>
          <Tooltip title="Order this vihecle">
            <CarRentalOutlinedIcon onClick={placeOrder} fontSize="large" />
          </Tooltip>
        </div>
      ) : null}
    </div>
  )
}

export default Post
