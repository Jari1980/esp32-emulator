import Card from "../Card/Card";
import "./DeviceStatusCard.css";

function DeviceStatusCard() {
  return (
    <Card title="Device Status">
      <div className="device-status">

        <div className="device-status__row">
          <span>Name</span>
          <strong>Virtual ESP32</strong>
        </div>

        <div className="device-status__row">
          <span>ID</span>
          <strong>esp32-001</strong>
        </div>

        <div className="device-status__row">
          <span>Status</span>
          <strong className="device-status__online">
            Running
          </strong>
        </div>

      </div>
    </Card>
  );
}

export default DeviceStatusCard;