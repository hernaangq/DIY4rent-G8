
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import './Mytools.css';
import moment from 'moment';



function Myalquiler(props) {

  console.log(props.herramientas);

    return (
    <div className="ToolList">
      {props.herramientas.map((item, index) => (

        <div className="ToolList-item" key={index}>
          
          
          
            <div className="tool-image-list" style={{ width: '300px', height:'150px', float: 'left' }}>
              <img src={`data:image/jpg;base64, ${item.herramienta.foto}`} alt="Tool" style={{ maxWidth: '50%', height: 'auto' }} />
            </div>


          {item.estrellasServicio == null ?   
          <Link to={`/valorar/${item.herramienta.id}`} style={{ textDecoration: 'none' }}>
          <p><b>Valorar</b></p>
          </Link>
          : null}

            <p><b>{item.herramienta.nombre}</b></p>
        </div>
      ))}
    </div>
  );
}
  
  export default Myalquiler;