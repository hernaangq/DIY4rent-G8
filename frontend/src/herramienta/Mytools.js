import logo1 from "../images/martillo.jpg";
import logo2 from "../images/sierra.jpg";
import logo3 from "../images/toolbasic.jpg";
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import './Mytools.css';

function Mytools(props) {
    const tools = [
      { name: "Herramienta1", owner: "Propietario1", src: logo1 },
      { name: "Herramienta2", owner: "Propietario2", src: logo1 },
      { name: "Herramienta3", owner: "Propietario3", src: logo1},
      { name: "Herramienta4", owner: "Propietario4", src: logo2 },
      { name: "Herramienta5", owner: "Propietario5", src: logo2 },
      { name: "Herramienta6", owner: "Propietario6", src: logo2 },
      { name: "Herramienta7", owner: "Propietario7", src: logo3 },
      { name: "Herramienta8", owner: "Propietario8", src: logo3 },
      { name: "Herramienta9", owner: "Propietario9", src: logo3 },
      { name: "Herramienta10", owner: "Propietario10", src: logo3 },
      { name: "Herramienta11", owner: "Propietario11", src: logo3 },
      { name: "Herramienta12", owner: "Propietario12", src: logo3 },
    ];
  
  
    return (
    <div className="ToolList">
      {props.herramientas.map((item, index) => (

        <div className="ToolList-item" key={index}>
          <Link to={"/herramientas-propias/" + index} style={{ textDecoration: 'none' }}>
            <div className="tool-image-list" style={{ width: '300px', height: '150px', float: 'left' }}>
              <img src={`data:image/jpg;base64, ${item.foto}`} alt="Tool" style={{ maxWidth: '50%', height: 'auto' }} />
            </div>
            <p><b>{item.nombre}</b></p>
          </Link>
          <div><strong> {item.precio}€/día</strong> </div>
          <div><strong>Estado:</strong> {item.estado}</div>
          <div>{new Date(item.fechaInicio).toLocaleString()} - {new Date(item.fechaFinal).toLocaleString()}</div>
          <p> </p>
          <Link to={"/tool/editar/" + index}>Editar</Link>

        </div>
      ))}
    </div>
  );
}
  
  export default Mytools;