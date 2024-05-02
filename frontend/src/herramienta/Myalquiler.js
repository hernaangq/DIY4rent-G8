
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
          
          
        

          {item.estrellasServicio == null ?   
          <Link to={`/valorar/${item.id}`} style={{ textDecoration: 'none' }}>
          <p><b>Valorar</b></p>
          </Link>
          : null}

          <Link to={"/herramientas/" + item.herramienta.id} style={{ textDecoration: 'none' }}>
          <div className="tool-image-list" style={{ width: '300px', height:'150px', float: 'left' }}>
              <img src={`data:image/jpg;base64, ${item.herramienta.foto}`} alt="Tool" style={{ maxWidth: '50%', height: 'auto' }} />
            </div>

          <p><b>{item.herramienta.nombre}</b></p>
          </Link>


          
        </div>
      ))}
    </div>
  );
}
  
  export default Myalquiler;