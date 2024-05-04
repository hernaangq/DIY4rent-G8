import logo1 from "../images/martillo.jpg";
import logo2 from "../images/sierra.jpg";
import logo3 from "../images/toolbasic.jpg";
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import './Mytools.css';
import moment from 'moment';
import axios from 'axios';


function Mytools(props) {

  const handleSubmit = async (event, index) => {
    console.log(index);
    const response = await axios.delete('https://localhost:8443/herramientas/' + index);
    window.location.reload();
  }

  
    return (
    <div className="ToolList" style={{ margin: '0 20px' }}>
      {props.herramientas.map((item, index) => (


        <div className="ToolList-item" key={index}>
          <Link to={"/herramientas-propias/" + index} style={{ textDecoration: 'none' }}>
            <div className="tool-image-list" style={{ width: '300px', height: '150px', float: 'left' }}>
              <img src={`data:image/jpg;base64, ${item.foto}`} alt="Tool" style={{ maxWidth: '50%', height: 'auto' }} />
            </div>
            <p><b>{item.nombre}</b></p>
          </Link>
          <div><strong> {item.precio}€/día</strong> </div>
          <div><strong>Estado:</strong> {item.estado === 'COMO_NUEVO' ? 'COMO NUEVO' : item.estado === 'MUY_BUENO' ? 'MUY BUENO' : item.estado}</div>
          <div>
            {item.fechaInicio && item.fechaFinal ? (
              <p className='text-center'>
                <span className='bold'>De:</span>{' '}
                { moment(item.fechaInicio, 'DD/MM/YYYY').format('DD/MM/YYYY') }
                &nbsp;|&nbsp;
                <span className='bold'>Hasta:</span>  {moment(item.fechaFinal, 'DD/MM/YYYY').format('DD/MM/YYYY')  }
              </p>
            ) : null}
          </div>
          <p> </p>
          <Link to={"/tool/editar/" + index}>Editar</Link>
          <p> </p>
          <button onClick={(e) => handleSubmit(e, item.id)}>Eliminar</button>
        </div>
      ))}
    </div>
  );
}
  
  export default Mytools;