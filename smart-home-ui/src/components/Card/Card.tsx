import type { ReactNode } from "react";
import "./Card.css";

interface Props {
  title: string;
  icon?: string;
  children: ReactNode;
}

export function Card({ title, icon, children }: Props) {
  return (
    <section className="card">
      <header className="card__header">
        <h2>{title}</h2>
      </header>

      <div className="card__content">{children}</div>
    </section>
  );
}
