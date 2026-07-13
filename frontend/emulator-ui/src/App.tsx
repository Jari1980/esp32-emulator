import Header from "./components/Header/Header";
import Dashboard from "./components/Dashboard/Dashboard";
import useEsp32WebSocket from "./hooks/useEsp32Websocket";

function App() {
  useEsp32WebSocket();
  return (
    <>
      <Header />
      <Dashboard />
    </>
  );
}

export default App;
