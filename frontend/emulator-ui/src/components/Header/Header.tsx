import "./Header.css";
import logo from "../../assets/logo.png";
import useWebSocketStatus from "../../hooks/useWebsocketStatus";

function Header() {
  const { status } = useWebSocketStatus();
  return (
    <header className="header">
      <div className="header__brand">
        <img className="header__logo" src={logo} alt="Home Surveillance logo" />

        <div>
          <h1>Home Surveillance</h1>
          <p>ESP32 Emulator</p>
        </div>
      </div>

      <div className="header__status">
        <span
          className={
            status === "CONNECTED"
              ? "status-dot status-dot--online"
              : "status-dot status-dot--offline"
          }
        />

        <span>{status === "CONNECTED" ? "Connected" : "Disconnected"}</span>
      </div>
    </header>
  );
}

export default Header;
