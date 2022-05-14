import "../../styles/globals.css";
import type { AppProps } from "next/app";
import Navbar from "../components/Navbar/Navbar";
import Layout from "../components/Layout";
import AuthContext from "../context/Auth";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <AuthContext>
        <Layout>
          <>
            <Navbar></Navbar>
            <Component {...pageProps} />
          </>
        </Layout>
      </AuthContext>
    </>
  );
}

export default MyApp;
