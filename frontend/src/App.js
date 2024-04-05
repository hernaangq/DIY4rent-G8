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
import Publicartool from "./herramienta/Publicartool";


import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar/>
        <Routes>
          <Route path="/" element={<ToolList />} />
          <Route path="/tool/:id" element={<Tool />} />
          <Route path="/tool/:id/editar" element={<Editartool />} />
          <Route path="/mytools" element={<Mytools />} />
          <Route path="/iniciar" element={<Inicio />} />
          <Route path="/registrar" element={<Registro />} />
          <Route path="/publicar" element={<Publicartool/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
