import { useEffect } from "react";

import websocketAdapter from "../websocket/WebSocketAdapter";

import { useEsp32 } from "../context/Esp32Context";

function useEsp32WebSocket() {
  const { setSnapshot } = useEsp32();

  useEffect(() => {
    websocketAdapter.setMessageListener((message: any) => {
      if (message.type !== "STATE") {
        return;
      }

      setSnapshot(message.payload);
    });
  }, [setSnapshot]);
}

export default useEsp32WebSocket;
