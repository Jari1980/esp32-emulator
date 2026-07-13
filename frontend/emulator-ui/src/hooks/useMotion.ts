import { useState } from "react";

function useMotion() {
  const [motionDetected, setMotionDetected] = useState(false);

  const triggerMotion = () => {
    setMotionDetected(true);
  };

  const resetMotion = () => {
    setMotionDetected(false);
  };

  return {
    motionDetected,
    triggerMotion,
    resetMotion,
  };
}

export default useMotion;