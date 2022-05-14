import React, { useEffect } from "react"
import { useUser } from "../context/Auth"
import { refresh } from "../services/api"

const useCallApi = (callback: Function) => {
  const { user, userUpdate } = useUser()

  useEffect(() => {
    const callApi = async () => {
      try {
        await callback()
      } catch (e) {
        if (user) {
          const newTokens = await refresh(user!.refresh_token)
          userUpdate(newTokens)
        }
      }
    }
    callApi()
  }, [callback, user, userUpdate])

  return null
}

export default useCallApi
