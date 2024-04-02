import logo from "./logo.svg";
import "./App.css";
import Navbar from "./main/Navbar";
import ToolList from "./main/ToolList";
import Tool from "./herramienta/Tool";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";

// Navbar.js

function App() {
  return (
    <div className="App">
      <Navbar />
      <hr style={{ margin: "0", borderTop: "2px solid black" }} />{" "}
      {/* Línea separadora */}
      <header className="App-header-custom">
        <div style={{ display: "flex", alignItems: "center" }}>
          <p className="Header-text" style={{ marginRight: "10px" }}>
            La plataforma líder de alquiler de herramientas
          </p>
          <img
            src={logo}
            alt="Foto"
            style={{ width: "100px", height: "100px" }}
          />{" "}
          {/* Cambia la ruta y el nombre del archivo según corresponda */}
        </div>
        <ToolList />
      </header>
    </div>
  );
}

export default App;
