import logo1 from "../images/martillo.jpg";
import logo2 from "../images/sierra.jpg";
import logo3 from "../images/toolbasic.jpg";
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import Tool from "../herramienta/Tool";
import './ToolList.css';
import logo from "../images/logo1ejemplov.png";
import axios from 'axios';
import { useState } from "react";
import { useEffect } from "react";


function ToolList() {
  

  const tools = [
    { name: "Herramienta1", owner: "Propietario1", src: logo1 },
    { name: "Herramienta2", owner: "Propietario2", src: logo2 },
    { name: "Herramienta3", owner: "Propietario3", src: logo3},
    { name: "Herramienta4", owner: "Propietario4", src: logo3 },
    { name: "Herramienta5", owner: "Propietario5", src: logo3 },
    { name: "Herramienta6", owner: "Propietario6", src: logo3 },
    { name: "Herramienta7", owner: "Propietario7", src: logo3 },
    { name: "Herramienta8", owner: "Propietario8", src: logo3 },
    { name: "Herramienta9", owner: "Propietario9", src: logo3 },
    { name: "Herramienta10", owner: "Propietario10", src: logo3 },
    { name: "Herramienta11", owner: "Propietario11", src: logo3 },
    { name: "Herramienta12", owner: "Propietario12", src: logo3 },
  ];
  const [items, setItems] = useState(tools);


// useEffect(() => {
//   setTimeout(() => {
//     setIsLoading(false);
//   },200)
// }, []);

useEffect(() => {
  callServer()
}, []);


const callServer = async () => {

      const response = await axios.get('http://localhost:8443/herramientas');
      // const datos = await response.json();
      setItems(response.data);
  }

  return (
    <div>
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
    
      <div className="ToolList">
        {items.map((item, index) => (
          <div className="ToolList-item" key={index}>
            <img src={item.foto} alt={item.name} />
            <div>{item.nombre}</div>
            <div>{item.precio}</div>
            <div>{item.estado}</div>
            <div>{new Date(item.fechasDisponibles).toLocaleString()}</div>
            <Link to="/tool/1">Link a la herramienta</Link>
          </div>
        ))}
      </div>
      </div>
  );
}

export default ToolList;
