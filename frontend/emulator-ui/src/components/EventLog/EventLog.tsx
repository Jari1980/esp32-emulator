import Card from "../Card/Card";
import "./EventLog.css";

function EventLog() {
  return (
    <Card title="Event Log">

      <div className="event-log">

        <div className="event-log__item">
          <span>12:10:02</span>
          <p>Temperature changed to 22.4°C</p>
        </div>

        <div className="event-log__item">
          <span>12:10:05</span>
          <p>Motion detected</p>
        </div>

        <div className="event-log__item">
          <span>12:10:08</span>
          <p>LED turned OFF</p>
        </div>

      </div>

    </Card>
  );
}

export default EventLog;