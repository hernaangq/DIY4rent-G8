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
import { jwtDecode } from "jwt-decode";

const Tool = (props) => {

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

  
    let username = getCurrentUsername(); // Cambiar por la función que obtiene el username del usuario actual

    const token = localStorage.getItem('token');

    function getCurrentUsername() {
      // Obtén el token JWT del almacenamiento local
      const token = localStorage.getItem('token');
    
      if (!token) {
        // Si no hay token, retorna null o algún valor por defecto
        return null;
      }
    
      // Decodifica el token JWT
      const decodedToken = jwtDecode(token);
    
      // Retorna el nombre de usuario del token decodificado
      return decodedToken.username;
    }
    //console.log(herramientaId);
    
    
    let response = await axios.post('https://localhost:8443/alquileres/' + username + '/' + herramientaId +'?fecha1='+ fecha1 + '&fecha2=' + fecha2, {}, {
      headers: {
          'Authorization': `Bearer ${token}`
      }
  });    
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

    const token = localStorage.getItem('token');

    response1 = await axios.get('https://localhost:8443/alquileres/herramienta/'+ herramientaId, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
});
    //const datos = await response.json();
    //response2 = await axios.get('https://localhost:8443/herramienta/1/foto');
    
  
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
      stars.push('🔨');
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

          <p>{renderEmojis()}  {estrellasNum} Martillos </p>
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


          <p style={{ fontSize: '30px' }}>Estado: <strong>{herramienta.estado === 'COMO_NUEVO' ? 'COMO NUEVO' : herramienta.estado === 'MUY_BUENO' ? 'MUY BUENO' : herramienta.estado}</strong></p>




          <div className="tool-description" style={{ alignItems: 'center' }}>

            <div style={{ color: 'black' }}>

                <div>
                  <Calendar
                    onChange={onChange}
                    value={date}
                    selectRange={true}
                    tileClassName={({ date, view }) => {
                      if(fechasYaAlquilado.find(x=>x===moment(date).format("DD/MM/YYYY"))){
                       return  'highlight'
                      }}}
                      minDate={moment(herramienta.fechaInicio, "DD/MM/YYYY").toDate()}
                      maxDate={moment(herramienta.fechaFinal, "DD/MM/YYYY").toDate()}
                  />
                </div>

            </div>
            {date.length > 0 ? (
              <p className='text-center'>
                <span className='bold'>De:</span>{' '}
                {date[0].toLocaleDateString()}
                &nbsp;|&nbsp;
                <span className='bold'>Hasta:</span> {date[1].toLocaleDateString()}
              </p>
            ) : (
              <p className='text-center'>
                <span className='bold'>Default selected date:</span>{' '}
                {date.toLocaleDateString()}
              </p>
            )}

            <p style={{ fontSize: '30px' }}>Precio: <strong>{herramienta.precio}€/día</strong></p>
            <button onClick={handleAlquilarClick} className='btn' >Alquílalo</button>
          </div>
          <br></br>
          {alquilado ?
            <div className="tool-description" style={{ alignItems: 'center' }}>
              <h3>¡Ponte en contacto con el propietario de la herramienta!</h3>
              <p>Escribe con correo a <strong>{herramienta.propietario.email}</strong></p>
              <button onClick={handleCorreoClick} className='btn' >Ir a correo</button>
            </div> : <div></div>}

        </div>


        <div className="google-map" style={{ justifyContent: 'center', height: '300px' }}>
          <h3 style={{ verticalAlign: 'top' }} >Localización</h3>
          <iframe src={`https://www.google.com/maps/embed?pb=!1m17!1m12!1m3!1d341488.5704903917!2d${herramienta.propietario.longitud}!3d${herramienta.propietario.latitud}!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m2!1m1!2zNDHCsDIzJzA2LjQiTiAywrAxMCcyNC4yIkU!5e0!3m2!1sen!2ses!4v1712511678535!5m2!1sen!2ses`} width="100" height="2000" style={{ justifyContent: 'center', borderRadius: '15px' }} allowfullscreen="" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
      </div>

      


    </div>
  );
};




export default Tool;