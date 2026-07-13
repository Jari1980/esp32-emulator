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

            <div>
              <p>{event.message}</p>

              <small>
                {event.deviceId} · {event.type}
              </small>
            </div>
          </div>
        ))}
      </div>
    </Card>
  );
}

export default EventLog;
