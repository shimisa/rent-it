import React, { useEffect, useState } from "react"
import { getPosts, Post } from "../services/api"
import PostComp from "./Post"
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
      {posts.map((post: Post, i) => (
        <div key={i}>
          <PostComp post={post} />
        </div>
      ))}
    </>
  )
}

export default Posts
