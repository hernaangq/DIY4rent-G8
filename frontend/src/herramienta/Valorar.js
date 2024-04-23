import React, { useEffect, useState } from 'react';
import './Tool.css'; // Importa el archivo de estilos para Tool
import toolImage from '../images/martillo.jpg'; // Importa la imagen de la herramienta
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
  let herramienta = props.herramientas[rutaId];
  //console.log(herramienta)
  let herramientaId = herramienta.id;
  let navigate = useNavigate();

  const [alquileres, setAlquileres] = useState([]);
  const [foto, setFoto] = useState(null);
  const [alquilado, setAlquilado] = useState(false);
  const [fechasYaAlquilado, setFechaYaAlquilado] = useState([]);

  const [date, onChange] = useState([new Date("2024-04-01"), new Date("2024-04-01")]);

  //onChange([new Date("2022-03-25"), new Date("2022-03-25")]);

  const fecha1 = date[0].getFullYear() + '-' + ((date[0]).getMonth()+1).toString().padStart(2,0) + '-' + date[0].getDate().toString().padStart(2,0) ;
  const fecha2 = date[1].getFullYear() + '-' + ((date[1]).getMonth()+1).toString().padStart(2,0) + '-' + date[1].getDate().toString().padStart(2,0) ;


  const handleAlquilarClick = async () => {

  
    let username = 'laurita'; // Cambiar por el username del usuario actual
    //console.log(herramientaId);
    
    let response = await axios.post('http://localhost:8443/alquileres/' + username + '/' + herramientaId +'?fecha1='+ fecha1 + '&fecha2=' + fecha2);
    
    //let respuesta = await axios.patch('http://localhost:8443/herramientas/' + herramientaId, {estaAlquilada: true});
    setTimeout(() => {
      setAlquilado(true);
    }, 1000);

  }

  const handleCorreoClick = () => {
    let email = herramienta.propietario.email;
    window.location.href = 'mailto:'+{email}+'.com';
  }




  useEffect(() => {
    callServer()
  }, []);

  let response1;
  let response2;
  const callServer = async () => {

    response1 = await axios.get('http://localhost:8443/alquileres/herramienta/1');
    //const datos = await response.json();
    //response2 = await axios.get('http://localhost:8443/herramienta/1/foto');
    
  
    setAlquileres(response1.data.filter(alquiler => alquiler.estrellasServicio !== null));

    
    const tuplasFechas = response1.data.map(alquiler => ({dia1: alquiler.fechaInicioAlquiler, dia2: alquiler.fechaFinalAlquiler}) )
    console.log(tuplasFechas);
    
    const fechas = tuplasFechas.map(tupla => enumerateDaysBetweenDates(tupla.dia1, tupla.dia2));


    const concatenatedDates = [].concat(...fechas);
    console.log(concatenatedDates);
    setFechaYaAlquilado(concatenatedDates);
    console.log(fechasYaAlquilado);
  }

  var enumerateDaysBetweenDates = function(startDate, endDate) {
    var dates = [];
    
    var currDate = moment(startDate, 'DD/MM/YYYY').startOf('day');
    var lastDate = moment(endDate, 'DD/MM/YYYY').startOf('day');
    dates.push(moment(currDate.clone().toDate()).format("DD/MM/YYYY"));

    while(currDate.add(1, 'days').diff(lastDate) < 2) {
        //console.log(moment(currDate.toDate()).format("DD/MM/YYYY"));
        dates.push(moment(currDate.clone().toDate()).format("DD/MM/YYYY"));
    }

    return dates;
};

  //let dateArr = enumerateDaysBetweenDates('21/03/2024', '26/03/2024');
  //console.log(dateArr.toString());

  console.log(herramienta.fechaInicio);



  var estrellasNum = alquileres.reduce((acc, alquiler) => {
    return acc + alquiler.estrellasServicio
  }, 0) / alquileres.length;

  estrellasNum = (Math.round(estrellasNum * 100) / 100).toFixed(2);
  
  const renderEmojis = () => {
    const stars = [];
    for (let i = 0; i < estrellasNum; i++) {
      stars.push('⭐');
    }
    return stars;
  };

 
  var rawResponse = herramienta.foto; 
  

  const valoraciones = alquileres.map((alquiler, index) => { return (<div key={index} className="card" style={{ backgroundColor: '#DDA15E', margin: '10px', padding: '10px', borderRadius: '5px', boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)', transition: '0.3s' }}><div className="card-body"><h5 className="card-title">{alquiler.usuario.nombre} {alquiler.usuario.apellidos}</h5><p className="card-text" style={{ fontSize : '20px', fontStyle:'italic'}}>"{alquiler.valoracion}"</p></div></div>); });
  
  return (
    <div className="container">
      <div className="tool-image" style={{float: 'none', marginLeft:'75px' }}>
        <img className='fotoVista' src={`data:image/jpg;base64, ${rawResponse}`} alt="Tool" style={{ verticalAlign: 'top', height: '400px', width: 'auto', borderRadius:'15px' }} />
        <div style={{ fontSize: '30px' }} className="ratings">

          <p>{renderEmojis()}   {estrellasNum} estrellas</p>
        </div>
        <div>

          <h3>Valoraciones</h3>
          <div className="valoracion-container">{valoraciones}</div>
        </div>
        <i>Propietario: {herramienta.propietario.nombre} {herramienta.propietario.apellidos}</i>



      </div>



      <div className="tool-details" style={{ maxWidth: '100%', width: '800px' }}>

        <div className="tool-info">
          <h1 style={{ fontSize: '50px' }}>{herramienta.nombre}</h1>


          <p style={{ fontSize: '30px' }}>Estado: <strong>{herramienta.estado}</strong></p>



        </div>


        
      </div>

      


    </div>
  );
};




export default Valorar;