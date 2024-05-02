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
  const [fechaInicio, setInicio] = useState('');
  const [fechaFinal, setFinal] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [foto, setFoto] = useState('');
  const [estado, setEstado] = useState('');



  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await axios.patch('https://localhost:8443/herramientas/' + herramienta.id, {
      nombre,
      precio,
      fechaInicio,
      fechaFinal,
      estado
    });

    if (foto) {
    let ruta2 = 'https://localhost:8443/herramientas/' + herramienta.id + '/foto';
    let formData = new FormData();
    formData.append('file', foto);
    const response2 = await axios.post(ruta2, formData, {
      headers: {
        "Accept": "*/*",
        'Content-Type': 'multipart/form-data'
     }
    });}
    
    setTimeout(() => {
      navigate('/misherramientas/:rutaId');
      window.location.reload();
    }, 1000);
  };
  return (
    <div className="editartool-container">
      <h2>Editar datos de {herramienta.nombre}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Nombre (obligatorio): </label>
          <input type="text" value={nombre} onChange={(e) => setNombre(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Precio:</label>
          <input type="text" value={precio} onChange={(e) => setPrecio(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Foto:</label>
          <input type="file" accept="*/*" onChange={(e) => {console.log(e.target.files[0]);  setFoto(e.target.files[0])}} className="form-control-file" />
        </div>
        <div className='form-group'>
          <label>Fecha de inicio:</label>
          <input type="date" id="fechaInicio" value={fechaInicio} onChange={(e) => setInicio(e.target.value)}   />
        </div>
        <div className='form-group'>
          <label>Fecha final:</label>
          <input type="date" id="fechaFinal" value={fechaFinal} onChange={(e) => setFinal(e.target.value)}  />
          </div>
        <div className="form-group">
        <label>Estado (obligatorio):</label>
          <select value={estado} onChange={(e) => setEstado(e.target.value)} className="form-control">
            <option value="">Selecciona un estado</option>
            <option value="COMO_NUEVO">Como nuevo</option>
            <option value="MUY_BUENO">Muy bueno</option>
            <option value="BUENO">Bueno</option>
            <option value="ACEPTABLE">Aceptable</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Guardar</button>
      </form>
    </div>
  );
}


export default Editartool;
