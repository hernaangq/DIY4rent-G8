import React, { useState, useEffect } from 'react';
import './Editartool.css';
import { useParams } from "react-router-dom";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Editartool = (props) => {

  let { rutaId } = useParams();
  console.log(props);
  let herramienta = props.herramientas[rutaId];
  console.log(herramienta);
  // let herramientaId = herramienta.id;
  let navigate = useNavigate();

  const [nombre, setNombre] = useState('');
  const [precio, setPrecio] = useState('');
  const [localizacion, setLocalizacion] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [foto, setFoto] = useState('');



  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await axios.patch('http://localhost:8443/herramientas/' + herramienta.id, {
      nombre,
      precio,
      localizacion
    });

    let ruta2 = 'http://localhost:8443/herramientas/' + herramienta.Id + '/foto';
    let formData = new FormData();
    formData.append('foto', foto);
    const response2 = await axios.put(ruta2, formData, {
      headers: {
        "Accept": "application/json",
        'Content-Type': 'multipart/form-data',
        'Access-Control-Allow-Origin': '*'
     }
    });

    setTimeout(() => {
      navigate('/');
    }, 1000);
  };
  return (
    <div className="editartool-container">
      <h2>Editar datos de {herramienta.nombre}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Nombre:</label>
          <input type="text" value={nombre} onChange={(e) => setNombre(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Precio:</label>
          <input type="text" value={precio} onChange={(e) => setPrecio(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Foto:</label>
          <input type="file" accept="image/*" onChange={(e) => {console.log(e.target.files[0]);  setFoto(e.target.files[0])}} className="form-control-file" />
        </div>
        <button type="submit" className="btn btn-primary">Guardar</button>
      </form>
    </div>
  );
}


export default Editartool;
