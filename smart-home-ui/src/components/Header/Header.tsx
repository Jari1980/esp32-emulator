import "./Header.css";
import logo from "../../assets/logo.png";
import { useSmartHome } from "../../context/SmartHomeContext";

export function Header() {
  const { connected } = useSmartHome();
  return (
    <header className="header">
      <div className="brand">
        <div className="header_logo">
          <img
            className="header__logo"
            src={logo}
            alt="Home Surveillance logo"
          />
        </div>

        <div>
          <h1>Broccoli Home</h1>

          <p>Smart Home Surveillance</p>
        </div>
      </div>

      <div className="status">
        <span className={connected ? "dot dot--online" : "dot dot--offline"} />

        {connected ? "Connected" : "Disconnected"}
      </div>
    </header>
  );
}
