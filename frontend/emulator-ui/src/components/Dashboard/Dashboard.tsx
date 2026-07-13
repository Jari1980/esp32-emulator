import "./Dashboard.css";
import DeviceStatusCard from "../DeviceStatusCard/DeviceStatusCard";
import CameraCard from "../CameraCard/CameraCard";
import TemperatureControlCard from "../TemperatureControlCard/TemperatureControlCard";
import MotionControlCard from "../MotionControlCard/MotionControlCard";
import LightControlCard from "../LightControlCard/LightControlCard";
import EventLog from "../EventLog/EventLog";
import Esp32StateCard from "../Esp32StateCard/Esp32StateCard";

function Dashboard() {
  return (
    <div className="dashboard">
      <CameraCard />

      <DeviceStatusCard />

      <TemperatureControlCard />

      <MotionControlCard />

      <LightControlCard />

      <Esp32StateCard />

      <div className="dashboard__full-width">
        <EventLog />
      </div>
    </div>
  );
}

export default Dashboard;
