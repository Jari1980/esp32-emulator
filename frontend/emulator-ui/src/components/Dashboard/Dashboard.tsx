import Card from "../Card/Card";
import "./Dashboard.css";
import DeviceStatusCard from "../DeviceStatusCard/DeviceStatusCard";
import CameraCard from "../CameraCard/CameraCard";
import TemperatureControlCard from "../TemperatureControlCard/TemperatureControlCard";
import MotionControlCard from "../MotionControlCard/MotionControlCard";

function Dashboard() {
  return (
    <div className="dashboard">

      <CameraCard />

      <DeviceStatusCard />

      <TemperatureControlCard />

      <MotionControlCard />

      <Card title="Event Log">
        <p>No events</p>
      </Card>

    </div>
  );
}

export default Dashboard;