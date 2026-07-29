import "./Header.css";
import logo from "../../assets/logo.png";

export function Header() {
  return (
    <header className="header">
      <div className="brand">
        <div className="logo-placeholder"><img className="header__logo" src={logo} alt="Home Surveillance logo" /></div>

        <div>
          <h1>Broccoli Home</h1>

          <p>Smart Home Surveillance</p>
        </div>
      </div>

      <div className="status">
        <span className="dot" />
        Online
      </div>
    </header>
  );
}
