import { useState } from "react";

function useTemperature() {
  const [temperature, setTemperature] = useState(22.4);

  const increase = () => {
    const newTemperature = Number((temperature + 0.1).toFixed(1));

    setTemperature(newTemperature);

    return newTemperature;
  };

  const decrease = () => {
    const newTemperature = Number((temperature - 0.1).toFixed(1));

    setTemperature(newTemperature);

    return newTemperature;
  };

  return {
    temperature,
    increase,
    decrease,
  };
}

export default useTemperature;
