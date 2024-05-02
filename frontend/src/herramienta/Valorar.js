import React, { useEffect, useState } from 'react';
import './Tool.css'; // Importa el archivo de estilos para Tool
import toolImage from '../images/martillo.png'; // Importa la imagen de la herramienta
import martillofinal from '../images/martilllofinal.png';

import { useParams } from "react-router-dom";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Calendar from 'react-calendar';
//import 'react-calendar/dist/Calendar.css';
//import 'react-calendar/dist/Calendar.css';
import styled from 'styled-components';
import moment from 'moment'

const Valorar = (props) => {

  let { rutaId } = useParams();
  //let herramienta = props.herramientas.find(item => item.id === Number(rutaId));
  //let alquiler = props.alquileres.find(item => item.id === Number(rutaId));



  let alquiler = props.alquileres.find(item => item.id === Number(rutaId));
  console.log(alquiler.herramienta);
  let herramienta = alquiler.herramienta;
  let herramientaId = herramienta.id;
  let navigate = useNavigate();

  const [alquileres, setAlquileres] = useState([]);
  const [selectedStars, setSelectedStars] = useState('');
  const [valoracionEscrita, setValoracionEscrita] = useState('');


 



  //let dateArr = enumerateDaysBetweenDates('21/03/2024', '26/03/2024');
  //console.log(dateArr.toString());


  const handleEnviarClick = async (event) => {
    event.preventDefault();

    // Obtén el token JWT del almacenamiento local
    const token = localStorage.getItem('token');

    // Obtén el ID de la herramienta
    const responseHerramienta = await axios.get('https://localhost:8443/alquileres/herramienta/' + herramientaId);
    const idAlquilada = rutaId;

    // Usa el ID de "alquilada" para hacer la petición PATCH
    const ruta = 'https://localhost:8443/alquileres/' + idAlquilada;

    const data = {
        estrellasServicio: selectedStars,
        valoracion: valoracionEscrita
    };

    try {
        const response = await axios.patch(ruta, data, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Incluye el token JWT en el encabezado
            }
        });
    } catch (error) {
        console.error('Error al valorar la herramienta:', error);
    }

    setTimeout(() => {
        navigate('/');
        window.location.reload();
    }, 1000);
};

  var estrellasNum = alquileres.reduce((acc, alquiler) => {
    return acc + alquiler.estrellasServicio
  }, 0) / alquileres.length;

  estrellasNum = (Math.round(estrellasNum * 100) / 100).toFixed(2);
  
  const renderEmojis = () => {
    const stars = [];
    for (let i = 0; i < estrellasNum; i++) {
      stars.push('🔨');
    }
    return stars;
  };

 
  var rawResponse = herramienta.foto; 
  console.log(alquileres)
  

  const valoraciones = alquileres.map((alquiler, index) => { console.log(alquiler.usuario.nombre); return (<div key={index} className="card" style={{ backgroundColor: '#DDA15E', margin: '10px', padding: '10px', borderRadius: '5px', boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)', transition: '0.3s' }}><div className="card-body"><h5 className="card-title">{alquiler.usuario.nombre} {alquiler.usuario.apellidos}</h5><p className="card-text" style={{ fontSize : '20px', fontStyle:'italic'}}>"{alquiler.valoracion}"</p></div></div>); });
  
return (
    <div className="container">
        <div className="tool-image" style={{float: 'none', marginLeft:'75px' }}>
            <img className='fotoVista' src={`data:image/jpg;base64, ${rawResponse}`} alt="Tool" style={{ verticalAlign: 'top', height: '400px', width: 'auto', borderRadius:'15px' }} />
        </div>



        <div className="tool-details" style={{ maxWidth: '100%', width: '800px' }}>

            <div className="tool-info">
                <h1 style={{ fontSize: '50px' }}>{herramienta.nombre}</h1>


                <div><strong>Estado:</strong> {herramienta.estado === 'COMO_NUEVO' ? 'COMO NUEVO' : herramienta.estado === 'MUY_BUENO' ? 'MUY BUENO' : herramienta.estado}</div>
                <div>


            </div>
            <div>
                    <h3>Escribe una valoración</h3>
                    <textarea
                            placeholder="Escribe tu valoración aquí..."
                            rows={4}
                            cols={50}
                            value={valoracionEscrita}
                            onChange={(e) => {
                                    setValoracionEscrita(e.target.value);
                            }}
                    ></textarea>
            </div>
            <div style={{ fontSize: '2em' }}>
                    <h3>Califica con martillos</h3>
                    <div>
                            {[1, 2, 3, 4, 5].map((star) => (
                                    <span
                                            key={star}
                                            style={{ cursor: 'pointer' }}
                                            onClick={() => {
                                                    setSelectedStars(Number(star));
                                            }}
                                    >
                                            {star <= selectedStars ? <img src={martillofinal} style={{ width: '30px', height: '30px' }} /> : <img src={toolImage} style={{ width: '30px', height: '30px' }} />}
                                    </span>
                            ))}
                    </div>
            </div>

     <button onClick={handleEnviarClick}>Enviar</button>

            
        </div>

        
    </div>

    </div>
);
};




export default Valorar;