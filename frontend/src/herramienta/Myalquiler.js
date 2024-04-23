
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import './Mytools.css';
import moment from 'moment';



function Myalquiler(props) {

    return (
    <div className="ToolList">
      {props.herramientas.map((item, index) => (


        <div className="ToolList-item" key={index}>
          <p> </p>
          <Link to={"/valorar/" + index} style={{ textDecoration: 'none' }}>
            <div className="tool-image-list" style={{ width: '300px', height:'150px', float: 'left' }}>
              <img src={`data:image/jpg;base64, ${item.foto}`} alt="Tool" style={{ maxWidth: '50%', height: 'auto' }} />
            </div>
            
            <p><b>Valorar herramienta</b></p>
            </Link>
            <p><b>{item.nombre}</b></p>
        </div>
      ))}
    </div>
  );
}
  
  export default Myalquiler;