import Header from "./components/Header/Header";
import Dashboard from "./components/Dashboard/Dashboard";
import useEsp32StatePublisher from "./hooks/useEsp32StatePublisher";

function App() {
  useEsp32StatePublisher();
  return (
    <>
      <Header />
      <Dashboard />
    </>
  );
}

export default App;
