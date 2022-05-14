import React, { useEffect, useState } from "react"
import { getPosts, Post } from "../../services/api"
import PostComp from "./Post"
type Props = {}

const Posts = () => {
  const [posts, setPosts] = useState([])
  useEffect(() => {
    const callGetPosts = async () => {
      const data = await getPosts()
      setPosts(data)
    }
    callGetPosts()
  }, [])
  
  return (
    <>
      {posts.map((post: Post, i) => (
        <div key={i}>
          <PostComp post={post} />
        </div>
      ))}
    </>
  )
}

export default Posts
