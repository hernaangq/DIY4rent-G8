import React, { useEffect, useState } from 'react';
import './Tool.css'; // Importa el archivo de estilos para Tool
import toolImage from '../images/martillo.jpg'; // Importa la imagen de la herramienta
import { useParams } from "react-router-dom";
import axios from 'axios';

const Tool = (props) => {

  let { herramientaId } = useParams();
  let herramienta = props.herramientas[herramientaId];

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

  const estrellasNum = alquileres.reduce((acc, alquiler) => {
    return acc + alquiler.estrellasServicio
  }, 0) / alquileres.length;

  
  const renderEmojis = () => {
    const stars = [];
    for (let i = 0; i < estrellasNum; i++) {
      stars.push('⭐');
    }
    return stars;
  };

  console.log(renderEmojis());
  
  var rawResponse = herramienta.foto; // truncated for example
  //console.log(rawResponse);

  var blob = new Blob( [ herramienta.foto ], { type: "image/jpg" } );
  var imageUrl = URL.createObjectURL( blob );;

  return (
    <div className="container">
      <div className="tool-image" style={{ width: '25vw' }}>
      
      <img src={imageUrl} alt="Tool" />
      </div>
      <div className="tool-details">
        <div className="tool-info">
          <h2>{herramienta.nombre}</h2>
          <p>Estado: {herramienta.estado}</p>
        </div>
        <div className="tool-description">
        </div>
        <div className="ratings">
          <h3>Ratings</h3>
          <p>{renderEmojis()}   {estrellasNum} estrellas</p>
          
        
        </div>
        <div className="location">
          <h3>Available Locations</h3>
          <p>Location 1</p> 
        </div>
      </div>
    </div>
  );
};

export default Tool;
