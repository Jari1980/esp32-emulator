import "./Card.css";

type CardProps = {
  title: string;
  children: React.ReactNode;
};

function Card({ title, children }: CardProps) {
  return (
    <section className="card">
      <header className="card__header">
        <h2>{title}</h2>
      </header>

      <div className="card__content">
        {children}
      </div>
    </section>
  );
}

export default Card;