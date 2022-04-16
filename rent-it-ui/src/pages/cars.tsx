import { useRouter } from "next/router";
import React, { FC, useEffect, useState } from "react";
import { useUser } from "../context/Auth";
import { getUserCars, Vehicle } from "../services/api";

type Props = {};

const Cars: FC = (props: Props) => {
  const { user } = useUser();
  const router = useRouter();
  const [cars, setCars] = useState<Vehicle[] | null>(null);

  useEffect(() => {
    const getCars = async () => {
      const cars: Vehicle[] = await getUserCars(
        user!.email,
        0,
        user!.access_token
      );
      setCars(cars);
    };
    if (!user) {
      router.push("/");
    } else {
      getCars();
    }
  }, [router, user]);

  if (!user) {
    return <>Loading...</>;
  }
  return (
    <>
      <h2>cars</h2>
      {cars?.map((car) => {
        return (
          <>
            <span>
                {car.licenseNo}
            </span>
            <span>
                {car.description}
            </span>
          </>
        );
      })}
    </>
  );
};

export default Cars;
