import React from "react";
import logo from "./logo.svg";
import "./App.css";
import Navbar from "./main/Navbar";
import ToolList from "./main/ToolList";
import Tool from "./herramienta/Tool";
import Mytools from "./herramienta/Mytools";
import Editartool from "./herramienta/Editartool"
import Inicio from "./registro/Inicio"
import Registro from "./registro/Registro"

import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar/>
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
        </header>
        <Routes>
          <Route path="/" element={<ToolList />} />
          <Route path="/tool/:id" element={<Tool />} />
          <Route path="/tool/:id/editar" element={<Editartool />} />
          <Route path="/mytools" element={<Mytools />} />
          <Route path="/iniciar" element={<Inicio />} />
          <Route path="/registrar" element={<Registro />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
