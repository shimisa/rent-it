import React, { FC, useEffect, useState } from "react"
import { useUser } from "../context/Auth"
import { Post } from "../services/api"
import styles from "../../styles/Home.module.css"

type Props = {}

const MyPosts: FC = (props: Props) => {
  const { user } = useUser()
  const [myPosts, setMyPosts] = useState([])
  useEffect(() => {
    const getUserPosts = async () => {
      const res = await fetch(
        `http://localhost:8080/api/post/postsbyuser?vehicleOwnerUsername=${user?.email}&page=0`,
        {
          method: "GET", // *GET, POST, PUT, DELETE, etc.
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: "Bearer " + user?.access_token, // access token
            // 'Content-Type': 'application/x-www-form-urlencoded'
          },
        }
      )
      const data = await res.json()
      console.log(data)

      setMyPosts(data)
    }

    getUserPosts()
  }, [user?.access_token, user?.email])

  return (
    <div>
      {myPosts.map((post: Post) => {
        return (
          <div key={post.postId} className={styles.post}>
            <span>
              <h2> {post.header}</h2>
              <p> {post.description}</p>
            </span>
            <span>
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
            </span>
            <span>
              <p>
                Available from {new Date(post.fromDate).toLocaleDateString()} To{" "}
                {new Date(post.tillDate).toLocaleDateString()}
              </p>
            </span>
          </div>
        )
      })}
    </div>
  )
}

export default MyPosts
