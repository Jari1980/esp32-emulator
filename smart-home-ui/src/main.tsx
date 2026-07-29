import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";

import { SmartHomeProvider } from "./context/SmartHomeContext";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <SmartHomeProvider>
      <App />
    </SmartHomeProvider>
  </React.StrictMode>,
);
