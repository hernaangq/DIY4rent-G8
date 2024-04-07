import React, { useEffect, useState } from 'react';
import './Tool.css'; // Importa el archivo de estilos para Tool
import toolImage from '../images/martillo.jpg'; // Importa la imagen de la herramienta
import { useParams } from "react-router-dom";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const Tool = (props) => {

  let { rutaId } = useParams();
  let herramienta = props.herramientas[rutaId];
  //console.log(herramienta)
  let herramientaId = herramienta.id;
  let navigate = useNavigate();


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
    setTimeout(() => {
      navigate('/');
    }, 1000);
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
  

  const valoraciones = alquileres.map(alquiler => {
    return (
      <div>
        <p>{alquiler.usuario.nombre} {alquiler.usuario.apellidos}: {alquiler.valoracion}</p> 
      </div>
    );
  });

  console.log(valoraciones);

  return (
    <div className="container">
      <div className="tool-image" style={{float: 'left' }}>
        <img src={`data:image/jpg;base64, ${rawResponse}`} alt="Tool" style={{  height: '550px', width: '450px', height: 'auto' }} />
        <div className="ratings">
          <h3>Ratings</h3>
          <p>{renderEmojis()}   {estrellasNum} estrellas</p>
        </div>
        <div className="valoracion">
          <h3>Valoraciones</h3>
          <div>{valoraciones}</div>
        </div>
      </div>
      
      <div className="tool-details" style={{ maxWidth: '100%', width: '800px' }}>
        <div className="tool-info">
          <h2>{herramienta.nombre}</h2>
          <p>Estado: {herramienta.estado}</p>
        </div>
        <div className="alquila">
          <button onClick={handleAlquilarClick}>Alquílalo</button>
        </div>

        <div className="location">
          <h3>Localización</h3>
        </div>
        <div class="google-map">
        <iframe src={`https://www.google.com/maps/embed?pb=!1m17!1m12!1m3!1d341488.5704903917!2d${herramienta.propietario.longitud}!3d${herramienta.propietario.latitud}!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m2!1m1!2zNDHCsDIzJzA2LjQiTiAywrAxMCcyNC4yIkU!5e0!3m2!1sen!2ses!4v1712511678535!5m2!1sen!2ses`} width="10" height="20" style={{border:'1'}} allowfullscreen="" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
      </div>
    </div>
  );
};

export default Tool;
