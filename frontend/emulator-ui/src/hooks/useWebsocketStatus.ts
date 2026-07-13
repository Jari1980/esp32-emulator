import { useEffect, useState } from "react";

import websocketAdapter from "../websocket/WebSocketAdapter";

import type { ConnectionStatus } from "../types/ConnectionStatus";

function useWebSocketStatus() {
  const [status, setStatus] = useState<ConnectionStatus>("DISCONNECTED");

  useEffect(() => {
    websocketAdapter.setStatusListener((newStatus) => {
      console.log("Status update:", newStatus);

      setStatus(newStatus);
    });

    websocketAdapter.connect();

    return () => {
      websocketAdapter.disconnect();
    };
  }, []);

  return {
    status,
  };
}

export default useWebSocketStatus;
