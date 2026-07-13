import Card from "../Card/Card";
import "./Dashboard.css";
import DeviceStatusCard from "../DeviceStatusCard/DeviceStatusCard";
import CameraCard from "../CameraCard/CameraCard";
import TemperatureControlCard from "../TemperatureControlCard/TemperatureControlCard";
import MotionControlCard from "../MotionControlCard/MotionControlCard";
import LightControlCard from "../LightControlCard/LightControlCard";

function Dashboard() {
  return (
    <div className="dashboard">
      <CameraCard />

      <DeviceStatusCard />

      <TemperatureControlCard />

      <MotionControlCard />

      <div className="dashboard__full-width">
        <LightControlCard />
      </div>

      <div className="dashboard__full-width">
        <Card title="Event Log">
          <p>No events</p>
        </Card>
      </div>
    </div>
  );
}

export default Dashboard;
