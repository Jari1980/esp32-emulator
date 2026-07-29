import type { ReactNode } from "react";
import { Header } from "../Header/Header";
import "./Layout.css";

interface Props {
  children: ReactNode;
}

export function Layout({ children }: Props) {
  return (
    <div className="app-layout">
      <Header />

      <main className="content">{children}</main>
    </div>
  );
}
