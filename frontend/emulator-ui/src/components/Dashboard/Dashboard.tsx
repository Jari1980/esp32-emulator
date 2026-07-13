import Card from "../Card/Card";
import "./Dashboard.css";
import DeviceStatusCard from "../DeviceStatusCard/DeviceStatusCard";

function Dashboard() {
  return (
    <div className="dashboard">

      <Card title="Camera">
        <p>No signal</p>
      </Card>

      <DeviceStatusCard />

      <Card title="Sensors">
        <p>Temperature: -- °C</p>
        <p>Motion: Unknown</p>
      </Card>

      <Card title="Controls">
        <p>Light: OFF</p>
        <button>Turn ON</button>
      </Card>

      <Card title="Event Log">
        <p>No events</p>
      </Card>

    </div>
  );
}

export default Dashboard;