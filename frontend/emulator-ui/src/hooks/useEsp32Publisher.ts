import { useEsp32 } from "../context/Esp32Context";
import { mapToSnapshot } from "../mapper/stateMapper";
import websocketAdapter from "../websocket/WebSocketAdapter";

function useEsp32Publisher() {
  const { state } = useEsp32();

  const publishState = () => {
    const snapshot = mapToSnapshot(state);

    websocketAdapter.sendState(snapshot);
  };

  return {
    publishState,
  };
}

export default useEsp32Publisher;
