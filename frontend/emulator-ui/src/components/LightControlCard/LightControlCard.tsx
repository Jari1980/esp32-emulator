import Card from "../Card/Card";
import useLight from "../../hooks/useLight";
import "./LightControlCard.css";

function LightControlCard() {
  const { isOn, toggle } = useLight();
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

        <button className="light-control__button" onClick={toggle}>
          Toggle Light
        </button>
      </div>
    </Card>
  );
}

export default LightControlCard;
