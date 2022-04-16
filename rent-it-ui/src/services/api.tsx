export interface AuthUserSuccess {
  first_name: string;
  access_token: string;
  refresh_token: string;
}

export interface AuthUserFailed  {
  fail: string;
}
export interface User {
  username: string;
  password: string;
}

export const encode = (msgDetails: User): string => {
  const formBody = [];
  let property: keyof typeof msgDetails;
  for (property in msgDetails) {
    const encodedKey = encodeURIComponent(property);
    const encodedValue = encodeURIComponent(msgDetails[property]);
    formBody.push(encodedKey + "=" + encodedValue);
  }
  const strFormBody = formBody.join("&");
  return strFormBody;
};

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
    });
    const data = await res.json();
    return data;
  } catch (err: any) {
    return { fail: "Incorect user name or password" };
  }
};
