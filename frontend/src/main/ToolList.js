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
import moment from 'moment';

function ToolList(props) {
  

console.log(props.herramientas);  

return (
  <div style={{ margin: '0 20px' }} >
    
    {/* Línea separadora */}
    <header className="App-header-custom">
      <div style={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
        <h1>La plataforma líder de alquiler de herramientas</h1>
        <img
          src={logo}
          alt="Foto"
          style={{ width: "70px", height: "70px" }}
        />{" "}
        {/* Cambia la ruta y el nombre del archivo según corresponda */}
      </div>
    </header>

      <div className="ToolList">
        {props.herramientas.map((item, index) => (
          <div className="ToolList-item" key={index}>
            <Link to={"/herramientas/" + index} style={{ textDecoration: 'none' }}>
            <div className="tool-image-list" style={{ width: '300px', height:'150px', float: 'left' }}>
              <img src={`data:image/jpg;base64, ${item.foto}`} alt="Tool" style={{ maxWidth: '50%', height: 'auto' }} />
            </div>
            
            </Link>
            <div><p><b>{item.nombre}</b></p></div>
            <div><strong> {item.precio}€/día</strong> </div>
            <div><strong>Estado:</strong> {item.estado === 'COMO_NUEVO' ? 'COMO NUEVO' : item.estado === 'MUY_BUENO' ? 'MUY BUENO' : item.estado}</div>
            {/* <div>{new Date(item.fechaInicio).getFullYear() + '-' + ((new Date(item.fechaInicio)).getMonth() + 1).toString().padStart(2, 0) + '-' + new Date(item.fechaInicio).getDate().toString().padStart(2, 0)}</div> */}
            <div>{ moment(item.fechaInicio, 'DD/MM/YYYY').format('DD/MM/YYYY') }</div>
            <div>{ moment(item.fechaFinal, 'DD/MM/YYYY').format('DD/MM/YYYY') }</div>    
            </div>
        ))}
      </div>
    </div>
  );
}

export default ToolList;
