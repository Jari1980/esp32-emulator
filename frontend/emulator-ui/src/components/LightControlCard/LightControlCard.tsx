import Card from "../Card/Card";
import "./LightControlCard.css";

function LightControlCard() {
  return (
    <Card title="Light / LED">

      <div className="light-control">

        <div className="light-control__state">
          OFF
        </div>

        <button className="light-control__button">
          Toggle Light
        </button>

      </div>

    </Card>
  );
}

export default LightControlCard;