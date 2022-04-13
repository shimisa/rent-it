import React from "react";
import styles from "../../styles/Home.module.css";

type Props = {
  children: React.ReactElement;
};

const Layout = ({ children }: Props) => {
  return <div className={styles.layout}>{children}</div>;
};

export default Layout;
