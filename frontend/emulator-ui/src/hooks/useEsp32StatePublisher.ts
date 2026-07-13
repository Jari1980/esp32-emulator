import { useEffect } from "react";

import { useEsp32 } from "../context/Esp32Context";
import { mapToSnapshot } from "../mapper/stateMapper";
import websocketAdapter from "../websocket/WebSocketAdapter";

function useEsp32StatePublisher() {
  const { state } = useEsp32();

  useEffect(() => {
    const snapshot = mapToSnapshot(state);

    websocketAdapter.sendState(snapshot);
  }, [state]);
}

export default useEsp32StatePublisher;
