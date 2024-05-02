
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import './Mytools.css';
import moment from 'moment';



function Myalquiler(props) {

  console.log(props.herramientas);

    return (
    <div className="ToolList" style={{ margin: '0 20px' }}>
      {props.herramientas.map((item, index) => (

        <div className="ToolList-item" key={index}>
          
          
        



          <Link to={"/herramientas/" + (item.herramienta.id - 1)} style={{ textDecoration: 'none' }}>
          <div className="tool-image-list" style={{ width: '300px', height:'150px', float: 'left' }}>
              <img src={`data:image/jpg;base64, ${item.herramienta.foto}`} alt="Tool" style={{ maxWidth: '50%', height: 'auto' }} />
            </div>

          <p><b>{item.herramienta.nombre}</b></p>
          </Link>

          <div><strong> {item.herramienta.precio}€/día</strong> </div>
          <div><strong>Estado:</strong> {item.herramienta.estado === 'COMO_NUEVO' ? 'COMO NUEVO' : item.herramienta.estado === 'MUY_BUENO' ? 'MUY BUENO' : item.herramienta.estado}</div>
          <div>
          <div>{ moment(item.fechaInicioAlquiler, 'DD/MM/YYYY').format('DD/MM/YYYY') }</div>
          <div>{ moment(item.fechaFinalAlquiler, 'DD/MM/YYYY').format('DD/MM/YYYY') }</div>            
            

          </div>


          {item.estrellasServicio == null ?   
          <Link to={`/valorar/${item.id}`} style={{ textDecoration: 'none' }}>
          <p><b>Valorar</b></p>
          </Link>
          : null}

          
        </div>
      ))}
    </div>
  );
}
  
  export default Myalquiler;