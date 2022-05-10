import React from "react"
import { addOrder, Post } from "../services/api"
import styles from "../../styles/Home.module.css"
import { useUser } from "../context/Auth"
import CarRentalOutlinedIcon from "@mui/icons-material/CarRentalOutlined"

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

  if (!user) {
    return null
  }

  return (
    <div key={post.postId} className={styles.post}>
      <div>
        <h2> {post.header}</h2>
        <p> {post.description}</p>
      </div>
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

      <div>
        <CarRentalOutlinedIcon onClick={placeOrder} fontSize="large" />
      </div>
    </div>
  )
}

export default Post
