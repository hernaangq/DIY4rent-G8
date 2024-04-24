import logo1 from "../images/martillo.jpg";
import logo2 from "../images/sierra.jpg";
import logo3 from "../images/toolbasic.jpg";
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import './Mytools.css';

function Mytools(props) {
  
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