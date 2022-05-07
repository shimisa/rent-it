import Image from "next/image";
import Link from "next/link";
import React, { useCallback, useState } from "react";
import styles from "../../styles/Home.module.css";
import { Button, Typography } from "@mui/material";
import Popout from "./Popout";
import { useUser } from "../context/Auth";
import { useRouter  } from "next/router";

type Props = {};

const Navbar = (props: Props) => {
  const [isLoginPopOpen, setIsLoginPopOpen] = useState<boolean>(false);
  const [isRegisterPopOpen, setIsRegisterPopOpen] = useState<boolean>(false);
  const router = useRouter()
  const { user } = useUser();

  const loginOpenClick = () => {
    setIsLoginPopOpen(true);
  };

  const registerOpenClick = () => {
    setIsRegisterPopOpen(true);
  };

  const loginCloseClick = useCallback(() => {
    setIsLoginPopOpen(false);
  }, []);

  const registerCloseClick = useCallback(() => {
    setIsRegisterPopOpen(false);
  }, []);

  return (
    <div className={styles.navbar}>
      <div className={styles.navLeftCol}>
        <Link href="/">
          <a>
            <Image src="/favicon.ico" alt="img" width={50} height={50} />
          </a>
        </Link>
      </div>
      {user ? (
        <span className={styles.navRightCol}>
          <Button onClick={() => {router.push('/cars')}}>My Cars</Button>
          <Button onClick={() => {router.push('/addCar')}}>Add Car</Button>
          <Button onClick={() => {router.push('/myPosts')}}>My Posts</Button>
          <h4>{user.email}</h4>
        </span>
      ) : (
        <div className={styles.navRightCol}>
          <Button onClick={loginOpenClick}>Login</Button>
          <Button onClick={registerOpenClick}>Register</Button>
        </div>
      )}
      {(isLoginPopOpen || isRegisterPopOpen) && (
        <Popout
          loginCloseClick={loginCloseClick}
          registerCloseClick={registerCloseClick}
          isLoginPopOpen={isLoginPopOpen}
          isRegisterPopOpen={isRegisterPopOpen}
        />
      )}
    </div>
  );
};

export default Navbar;
