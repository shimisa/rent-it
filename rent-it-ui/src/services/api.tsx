import { CarAccessories } from "../pages/addCar"

export interface AuthUserSuccess {
  email: string
  access_token: string
  refresh_token: string
}

export interface User {
  username: string
  password: string
}
export interface Vehicle {
  licenseNo: number
  typeOfVehicle: string
  model: string
  year: number
  gearType: string
  engineType: string
  description: string
  carAccessories: string[]
}

export interface Post {
  carAccessories: string[]
  carDescription: string
  description: string
  engineType: string
  fromDate: string
  gearType: string
  header: string
  model: string
  postId: number
  postedAt: string
  ratingOfRental: {
    avg_carAsDescribed: number
    avg_communication: number
    avg_deliveryTime: number
    avg_wouldRecommendToFriend: number
    carRental: number
    id: number
    numOfNegatives: number
    numOfNeutrals: number
    numOfPositives: number
    numOfVotes: number
    percentage_positiveFeedback: number
  }
  rentalFirstName: string
  tillDate: string
  typeOfVehicle: string
  year: number
}

export const encode = (msgDetails: User): string => {
  const formBody = []
  let property: keyof typeof msgDetails
  for (property in msgDetails) {
    const encodedKey = encodeURIComponent(property)
    const encodedValue = encodeURIComponent(msgDetails[property])
    formBody.push(encodedKey + "=" + encodedValue)
  }
  const strFormBody = formBody.join("&")
  return strFormBody
}

export const login = async (msg: string) => {
  try {
    const res = await fetch("http://localhost:8080/api/login", {
      method: "POST", // *GET, POST, PUT, DELETE, etc.
      headers: {
        // "Content-Type": "application/json",
        Accept: "application/json",
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: msg,
    })
    const data = await res.json()
    return data
  } catch (err: any) {
    return { fail: "Incorect user name or password" }
  }
}

export const register = async ({
  firstName,
  lastName,
  email,
  password,
  verifiedPassword,
}: {
  firstName: string
  lastName: string
  email: string
  password: string
  verifiedPassword: string
}) => {
  const res = await fetch("http://localhost:8080/api/registration", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      // 'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: JSON.stringify({
      firstName,
      lastName,
      email,
      password,
      verifiedPassword,
    }),
  })
  return res
}

export const registerConfirm = async (token: string) => {
  const res = await fetch(
    `http://localhost:8080/api/registration/confirm?token=${token}`,
    {
      method: "GET", // *GET, POST, PUT, DELETE, etc.
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded'
      },
    }
  )
  return res
}

export const getUserCars = async (
  email: string,
  page: number,
  token: string
) => {
  const res = await fetch(
    `http://localhost:8080/api/vehiclesofuser?email=${email}&page=${page}`,
    {
      method: "GET", // *GET, POST, PUT, DELETE, etc.
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: "Bearer " + token, // access token
        // 'Content-Type': 'application/x-www-form-urlencoded'
      },
    }
  )
  const data = await res.json()
  return data
}

export const addNewCar = async (data: object, token: string) => {
  const res = await fetch("http://localhost:8080/api/vehicle/save", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      Authorization: "Bearer " + token, // access token
      // 'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: JSON.stringify(data),
  })
  return res
}

export const addNewPost = async (data: object, token: string) => {
  const res = await fetch("http://localhost:8080/api/post/post", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      Authorization: "Bearer " + token, // access token
      // 'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: JSON.stringify(data),
  })
  return res
}

export const addOrder = async (data: object, token: string) => {
  const res = await fetch("http://localhost:8080/api/order/placeorder", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      Authorization: "Bearer " + token, // access token
      // 'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: JSON.stringify(data),
  })
  return res
}

export const getPosts = async () => {
  try {
    const res = await fetch("http://localhost:8080/api/post/posts?page=0")
    const data = await res.json()
    return data
  } catch (error) {
    alert("cant get posts")
  }
}
export const getUserOrders = async ({
  email,
  token,
}: {
  token: string
  email: string
}) => {
  try {
    const res = await fetch(
      `http://localhost:8080/api/order/getuserorders?email=${email}&page=${0}`,
      {
        method: "GET", // *GET, POST, PUT, DELETE, etc.
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: "Bearer " + token, // access token
          // 'Content-Type': 'application/x-www-form-urlencoded'
        },
      }
    )
    const data = await res.json()
    return data
  } catch (error) {
    alert("cant get posts")
  }
}

export const getUserOffers = async ({
  email,
  token,
}: {
  token: string
  email: string
}) => {
  try {
    const res = await fetch(
      `http://localhost:8080/api/order/getuseroffers?email=${email}&page=${0}`,
      {
        method: "GET", // *GET, POST, PUT, DELETE, etc.
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: "Bearer " + token, // access token
          // 'Content-Type': 'application/x-www-form-urlencoded'
        },
      }
    )
    const data = await res.json()
    return data
  } catch (error) {
    alert("cant get posts")
  }
}
