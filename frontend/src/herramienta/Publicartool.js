import React, { useState } from 'react';
import './Publicartool.css'; // Importar el archivo de estilos
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function Publicartool() {
  const [nombre, setNombre] = useState('');
  // const [descripcion, setDescripcion] = useState('');
  const [precio, setPrecio] = useState('');
  const [foto, setFoto] = useState('');
  const [estado, setEstado] = useState('');
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
    let id = 1; // Cambiar por el id del usuario actual
    let ruta = 'http://localhost:8443/herramientas/' + id;
    let herramientaId;
    try {
      let formData = new FormData();
      formData.append('foto', foto);
      const response = await axios.post(ruta, {
        nombre,
        precio,
        estado,
        formData,
        estaAlquilada: false
      });
      herramientaId = response.data.id;
    } catch (error) { };

    // try {
    //   // console.log((await axios.get('http://localhost:8443/herramientas/' + herramientaId)).data);
    //   console.log(herramientaId);
    //   let ruta2 = 'http://localhost:8443/herramientas/' + herramientaId + '/foto';
    //   let formData = new FormData();
    //   formData.append('foto', foto);
    //   console.log(formData);
    //   const response2 = await axios.put(ruta2, formData, {
    //     headers: {
    //       Accept: "*/*",
    //       'Content-Type': 'application/jpg',
    //       'Access-Control-Allow-Origin': '*'
    //     }
    //   });
    // } catch (error) { };
    setTimeout(() => {
      navigate('/');
    }, 1000);
  };

  const handleFoto = (event) => {
    event.preventDefault();
    const file = event.target.files[0];
    if (file && file.type === 'image/jpeg') {
      setFoto(file);
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
        {/* <div className="form-group">
          <label htmlFor="descripcion">Descripción:</label>
          <textarea
            id="descripcion"
            value={descripcion}
            onChange={(event) => setDescripcion(event.target.value)}
            required
          ></textarea>
        </div> */}
        <div className="form-group">
          <label>Estado:</label>
          <select value={estado} onChange={(e) => setEstado(e.target.value)} className="form-control">
            <option value="">Selecciona un estado</option>
            <option value="COMO NUEVO">Como nuevo</option>
            <option value="MUY BUENO">Muy bueno</option>
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
