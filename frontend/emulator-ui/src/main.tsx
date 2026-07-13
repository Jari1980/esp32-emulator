import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import { EventProvider } from "./context/EventContext";
import { Esp32Provider } from "./context/Esp32Context";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Esp32Provider>
      <EventProvider>
        <App />
      </EventProvider>
    </Esp32Provider>
  </StrictMode>,
);
