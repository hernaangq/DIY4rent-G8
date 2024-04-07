import React, { useEffect, useState } from 'react';
import './Tool.css'; // Importa el archivo de estilos para Tool
import toolImage from '../images/martillo.jpg'; // Importa la imagen de la herramienta
import { useParams } from "react-router-dom";
import axios from 'axios';

const Tool = (props) => {

  let { rutaId } = useParams();
  let herramienta = props.herramientas[rutaId];
  //console.log(herramienta)
  let herramientaId = herramienta.id;

  const handleAlquilarClick = async () => {
    const body = {
      herramienta: {
        estaAlquilada: true
      }
    };
    let id = 1; // Cambiar por el id del usuario actual
    let response = await axios.post('http://localhost:8443/alquileres/' + id + '/' + herramientaId, {});
    let respuesta = await axios.patch('http://localhost:8443/herramientas/' + herramientaId, {estaAlquilada: true});
    //console.log(respuesta);
    
    // const datos = await response.json();
  }

  const [alquileres, setAlquileres] = useState([]);
  const [foto, setFoto] = useState(null);

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
    //console.log(alquileres);
    //setFoto(response2.data);
    //console.log(response2.data);
  }

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

  //console.log(renderEmojis());
  
  var rawResponse = herramienta.foto; 
  

  const valoraciones = alquileres.map((alquiler, index) => { return (<div key={index} className="card" style={{ backgroundColor: '#DDA15E', margin: '10px', padding: '10px', borderRadius: '5px', boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)', transition: '0.3s' }}><div className="card-body"><h5 className="card-title">{alquiler.usuario.nombre} {alquiler.usuario.apellidos}</h5><p className="card-text" style={{ fontSize : '20px', fontStyle:'italic'}}>"{alquiler.valoracion}"</p></div></div>); });
  console.log(valoraciones);

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
          <h1 style={{fontSize : '50px'}}>{herramienta.nombre}</h1>
  
          <p style={{fontSize : '30px'}}>Estado: <strong>{herramienta.estado}</strong></p>

          <div className="tool-description" style={{alignItems:'center'}}>
          <p>Fecha Inicial: {new Date(herramienta.fechaInicio).toLocaleString()}</p>
          <p>Fecha Final: {new Date(herramienta.fechaFinal).toLocaleString()}</p>
          <p style={{fontSize : '30px'}}>Precio: <strong>{herramienta.precio}€/día</strong></p>
          <button onClick={handleAlquilarClick} className='btn' >Alquílalo</button>
          </div>
         
           </div>


        <div className="google-map" style={{ justifyContent: 'center', height: '300px'}}>
          <h3 style={{ verticalAlign: 'top'}} >Localización</h3>
          <iframe src={`https://www.google.com/maps/embed?pb=!1m17!1m12!1m3!1d341488.5704903917!2d${herramienta.propietario.longitud}!3d${herramienta.propietario.latitud}!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m2!1m1!2zNDHCsDIzJzA2LjQiTiAywrAxMCcyNC4yIkU!5e0!3m2!1sen!2ses!4v1712511678535!5m2!1sen!2ses`} width="100" height="2000"  style={{ justifyContent: 'center', borderRadius:'15px'}} allowfullscreen="" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
      </div>
    </div>
  );
};

export default Tool;
