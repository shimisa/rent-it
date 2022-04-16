export interface AuthUserSuccess {
  email: string;
  access_token: string;
  refresh_token: string;
}

export interface AuthUserFailed {
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
export const register = async ({
  firstName,
  lastName,
  email,
  password,
  verifiedPassword,
}: {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  verifiedPassword: string;
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
  });
  return res;
};
export const registerConfirm = async (token : string ) => {
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
  );
  return res;
};
