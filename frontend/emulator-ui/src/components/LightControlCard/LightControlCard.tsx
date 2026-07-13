import Card from "../Card/Card";
import useLight from "../../hooks/useLight";
import { useEvents } from "../../context/EventContext";
import "./LightControlCard.css";

function LightControlCard() {
  const { isOn, toggle } = useLight();

  const handleToggle = () => {
    toggle();

    addEvent(isOn ? "LED turned OFF" : "LED turned ON");
  };

  const { addEvent } = useEvents();
  return (
    <Card title="Light / LED">
      <div className="light-control">
        <div
          className={
            isOn
              ? "light-control__state light-control__state--on"
              : "light-control__state"
          }
        >
          {isOn ? "ON" : "OFF"}
        </div>

        <button className="light-control__button" onClick={handleToggle}>
          Toggle Light
        </button>
      </div>
    </Card>
  );
}

export default LightControlCard;
