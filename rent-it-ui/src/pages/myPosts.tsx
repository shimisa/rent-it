import React, { FC, useEffect, useState } from "react"
import { useUser } from "../context/Auth"
import { deletePost, Post } from "../services/api"
import styles from "../../styles/Home.module.css"
import CancelIcon from "@mui/icons-material/Cancel"
import Tooltip from "@mui/material/Tooltip"
import { useRouter } from "next/router"

type Props = {}

const MyPosts: FC = (props: Props) => {
  const router = useRouter()
  const { user } = useUser()
  const [myPosts, setMyPosts] = useState<Post[] | []>([])
  const [forceRender, setForceRender] = useState<boolean>(false)

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
      if (res.status === 200) {
        const data = await res.json()
        setMyPosts(data)
      }
    }

    getUserPosts()
  }, [user?.access_token, user?.email, forceRender])

  useEffect(() => {
    if (!user) {
      router?.push("/")
    }
  }, [router, user])

  const callDeletePost = async (postId: number) => {
    const res = await deletePost({ postId, token: user!.access_token })
    if (res === 200) {
      setForceRender((p) => !p)
    }
  }

  return (
    <div>
      {myPosts?.map((post: Post) => {
        return (
          <div key={post.postId} className={styles.post}>
            <div>
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
                  Available from {new Date(post.fromDate).toLocaleDateString()}{" "}
                  To {new Date(post.tillDate).toLocaleDateString()}
                </p>
              </span>
            </div>
            <Tooltip title="Delete post">
              <CancelIcon onClick={() => callDeletePost(post.postId)} />
            </Tooltip>
          </div>
        )
      })}
    </div>
  )
}

export default MyPosts
