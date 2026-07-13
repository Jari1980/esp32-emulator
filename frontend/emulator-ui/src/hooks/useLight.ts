import { useState } from "react";

function useLight() {
  const [isOn, setIsOn] = useState(false);

  const toggle = () => {
    setIsOn((value) => !value);
  };

  return {
    isOn,
    toggle,
  };
}

export default useLight;