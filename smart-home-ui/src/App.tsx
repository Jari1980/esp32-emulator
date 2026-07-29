import { useDeviceCommand } from "./hooks/useDeviceCommand";

function App() {
  const { sendCommand } = useDeviceCommand();

  return (
    <main>
      <h1>Smart Home UI</h1>

      <button onClick={() => sendCommand("TURN_ON", "led-001")}>
        Test LED ON
      </button>

      <button onClick={() => sendCommand("TURN_OFF", "led-001")}>
        Test LED OFF
      </button>
    </main>
  );
}

export default App;
