import { useEffect } from "react";
import { ControlUnitWebSocket } from "./websocket";
import { useSmartHome } from "./context/SmartHomeContext";

function App() {
  const { state } = useSmartHome();
  useEffect(() => {
    const websocket = new ControlUnitWebSocket();

    websocket.onMessage((message) => {
      console.log("STATE:", message.payload);
    });

    websocket.connect();
  }, []);

  return (
    <main>
      <h1>Smart Home UI</h1>

      <pre>{JSON.stringify(state, null, 2)}</pre>
    </main>
  );
}

export default App;
