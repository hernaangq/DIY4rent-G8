import React, { useState } from 'react';
import './Publicartool.css'; // Importar el archivo de estilos
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Calendar from 'react-calendar';
import moment from 'moment';



function Publicartool() {
  const [nombre, setNombre] = useState('');
  // const [descripcion, setDescripcion] = useState('');
  const [precio, setPrecio] = useState('');
  const [foto, setFoto] = useState('');
  const [estado, setEstado] = useState('');
  const [fechainicio, setInicio] = useState('');
  const [fechafinal, setFinal] = useState('');  
  // const [, set] = useState('');
  const navigate = useNavigate();

  // Leer la imagen como un búfer


  const handleSubmit = async (event) => {
    event.preventDefault();
    // Aquí puedes enviar los datos del formulario a tu servidor o hacer cualquier otra acción necesaria
    //   fs.readFile(foto, (error, data) => {
    // if (error) {
    //   console.error('Error al leer la imagen:', error);
    //   return;
    // }
    // // Convertir el búfer de la imagen a una cadena Base64
    // const base64String = data.toString('base64');});
    let username = localStorage.getItem('nombreUsuario') ; // Cambiar por el id del usuario actual

    let ruta = 'https://localhost:8443/herramientas/' + username;
    let herramientaId;
  
    const data = {
      nombre: nombre,
      precio: precio,
      estado: estado,
      fechaInicio: fechainicio,
      fechaFinal: fechafinal,
      estaAlquilada: false,
    };

    try {
      const response = await axios.post(ruta, data);
      herramientaId = response.data.id;
      console.log('Herramienta ID:', herramientaId);
    } catch (error) {
      console.error('Error al crear la herramienta:', error);
    }

    if (herramientaId && foto) {
      let ruta2 = 'https://localhost:8443/herramientas/' + herramientaId + '/foto';
      const formData = new FormData();
      formData.append('file', foto);

      try {
        const response2 = await axios.post(ruta2, formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        console.log('Foto subida con éxito');
      } catch (error) {
        console.error('Error al subir la foto:', error);
      }
    }
  
    setTimeout(() => {
      navigate('/');
      window.location.reload();
    }, 1000);
  };

  const handleFoto = async (event) => {
    event.preventDefault();
    const file = event.target.files[0];
    if (file && (file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/jpg' || file.type === 'image/gif')) {
      setFoto(file);
      console.log(file);
    } else {
      alert('Please upload a JPEG image');
    }
  };

  return (
    <div className="publicar-tool-container">
      <h2>Publicar una Herramienta</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="nombre">Nombre:</label>
          <input
            type="text"
            id="nombre"
            value={nombre}
            onChange={(event) => setNombre(event.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="nombre">Precio:</label>
          <input
            type="text"
            id="precio"
            value={precio}
            onChange={(event) => setPrecio(event.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="foto">Adjuntar Foto:</label>
          <input
            type="file"
            id="foto"
            accept="image/*"
            onChange={handleFoto}
            required
          />
        </div>

        <div className='form-group'>
          <label>Fecha de inicio:</label>
          <input type="date" id="fechaInicio" value={fechainicio} onChange={(event) => setInicio(event.target.value)} required />
        </div>
        <div className='form-group'>
          <label>Fecha de final:</label>
          <input type="date" id="fechaFinal" value={fechafinal} onChange={(event) => setFinal(event.target.value)} required />
        </div>



        <div className="form-group">
          <label>Estado:</label>
          <select value={estado} onChange={(e) => setEstado(e.target.value)} className="form-control">
            <option value="">Selecciona un estado</option>
            <option value="COMO_NUEVO">Como nuevo</option>
            <option value="MUY_BUENO">Muy bueno</option>
            <option value="BUENO">Bueno</option>
            <option value="ACEPTABLE">Aceptable</option>
          </select>
        </div>

        <button type="submit">Publicar</button>
      </form>
    </div>
  );
}

// </div>
//         <label htmlFor="precio">Precio:</label>
//           <input
//             type="text"
//             id="precio"
//             value={precio}
//             onChange={(event) => setPrecio(event.target.value)}
//             required
//           />
//         </div>
export default Publicartool;
