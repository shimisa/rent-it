import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import styles from '../../styles/Home.module.css'
import Posts from '../components/Posts'

const Home: NextPage = () => {
  return (
    <div className={styles.container}>
      <Head>
        <title>Rent-It</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <h1>
        Welcome To Rent-It 
      </h1>

      <Posts/>
    </div>
  )
}

export default Home
