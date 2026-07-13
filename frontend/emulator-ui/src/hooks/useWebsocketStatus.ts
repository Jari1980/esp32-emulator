import { useEffect, useState } from "react";

import websocketAdapter from "../websocket/WebSocketAdapter";

import type { ConnectionStatus } from "../types/ConnectionStatus";

function useWebSocketStatus() {
  const [status, setStatus] = useState<ConnectionStatus>("DISCONNECTED");

  useEffect(() => {
    websocketAdapter.connect();

    setStatus(websocketAdapter.getStatus());

    return () => {
      websocketAdapter.disconnect();

      setStatus(websocketAdapter.getStatus());
    };
  }, []);

  return {
    status,
  };
}

export default useWebSocketStatus;
