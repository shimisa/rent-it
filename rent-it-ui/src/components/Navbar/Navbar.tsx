import Image from "next/image"
import Link from "next/link"
import React, { useCallback, useState } from "react"
import styles from "./Navbar.module.css"
import { Button, Typography } from "@mui/material"
import Popout from "../Popout"
import { UserUpdateContext, useUser } from "../../context/Auth"
import { useRouter } from "next/router"

type Props = {}

const Navbar = (props: Props) => {
  const [isLoginPopOpen, setIsLoginPopOpen] = useState<boolean>(false)
  const [isRegisterPopOpen, setIsRegisterPopOpen] = useState<boolean>(false)
  const router = useRouter()
  const { user, userUpdate } = useUser()

  const loginOpenClick = () => {
    setIsLoginPopOpen(true)
  }

  const registerOpenClick = () => {
    setIsRegisterPopOpen(true)
  }

  const loginCloseClick = useCallback(() => {
    setIsLoginPopOpen(false)
  }, [])

  const registerCloseClick = useCallback(() => {
    setIsRegisterPopOpen(false)
  }, [])

  return (
    <div className={styles.navbar}>
      <div>
        <Link href="/">
          <a>
            <Image src="/favicon.ico" alt="img" width={50} height={50} />
          </a>
        </Link>
      </div>
      {user ? (
        <span>
          <Button
            onClick={() => {
              router.push("/myCars")
            }}
          >
            My Cars
          </Button>
          <Button
            onClick={() => {
              router.push("/addCar")
            }}
          >
            Add Car
          </Button>
          <Button
            onClick={() => {
              router.push("/myPosts")
            }}
          >
            My Posts
          </Button>
          <Button
            onClick={() => {
              router.push("/myOrders")
            }}
          >
            My Orders
          </Button>
          <Button
            onClick={() => {
              router.push("/myOffers")
            }}
          >
            My Offers
          </Button>
          <Button
            onClick={() => {
              router.push("/")
              userUpdate(null)
            }}
          >
            Log Out
          </Button>
        </span>
      ) : (
        <div>
          <Button onClick={loginOpenClick}>Login</Button>
          <Button onClick={registerOpenClick}>Register</Button>
        </div>
      )}
      {isLoginPopOpen && (
        <Popout
          closeClick={loginCloseClick}
          isPopOpen={isLoginPopOpen}
          type="Login"
        />
      )}
      {isRegisterPopOpen && (
        <Popout
          closeClick={registerCloseClick}
          isPopOpen={isRegisterPopOpen}
          type="Register"
        />
      )}
    </div>
  )
}

export default Navbar
