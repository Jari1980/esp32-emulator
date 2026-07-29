import { useEffect } from "react";
import { ControlUnitWebSocket } from "./websocket";

function App() {
  useEffect(() => {
    const websocket = new ControlUnitWebSocket();
    websocket.connect();
  }, []);

  return (
    <main>
      <h1>Smart Home UI</h1>
    </main>
  );
}

export default App;