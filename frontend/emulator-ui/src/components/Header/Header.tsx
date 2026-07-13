import "./Header.css";
import logo from "../../assets/logo.png";

function Header() {
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
        <span className="status-dot" />
        <span>Disconnected</span>
      </div>
    </header>
  );
}

export default Header;
