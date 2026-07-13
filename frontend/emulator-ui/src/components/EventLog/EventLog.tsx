import Card from "../Card/Card";
import { useEvents } from "../../context/EventContext";
import "./EventLog.css";

function EventLog() {
  const { events } = useEvents();
  return (
    <Card title="Event Log">
      <div className="event-log">
        {events.map((event) => (
          <div key={event.id} className="event-log__item">
            <span>{event.timestamp}</span>

            <p>{event.message}</p>
          </div>
        ))}
      </div>
    </Card>
  );
}

export default EventLog;
