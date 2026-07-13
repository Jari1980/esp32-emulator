import { useState } from "react";

function useTemperature() {
  const [temperature, setTemperature] = useState(22.4);

  const increase = () => {
    setTemperature((value) => Number((value + 0.1).toFixed(1)));
  };

  const decrease = () => {
    setTemperature((value) => Number((value - 0.1).toFixed(1)));
  };

  return {
    temperature,
    increase,
    decrease,
  };
}

export default useTemperature;