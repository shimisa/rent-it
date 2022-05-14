import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import styles from '../../styles/Home.module.css'
import Posts from '../components/Posts/Posts'

const Home: NextPage = () => {
  return (
    <div>
      <Head>
        <title>Rent-It</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Posts/>
    </div>
  )
}

export default Home
