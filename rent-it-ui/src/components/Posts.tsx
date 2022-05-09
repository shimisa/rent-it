import React, { useEffect, useState } from "react"
import { getPosts, Post } from "../services/api"
import styles from "../../styles/Home.module.css"

type Props = {}

const Posts = () => {
  const [posts, setPosts] = useState([])
  useEffect(() => {
    const callGetPost = async () => {
      const data = await getPosts()
      setPosts(data)
    }
    callGetPost()
  }, [])
  return (
    <>
      <h2>posts</h2>
      {posts.map((post: Post) => {
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
    </>
  )
}

export default Posts
