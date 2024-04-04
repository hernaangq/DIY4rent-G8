import React, { useState } from 'react';
import './Publicartool.css'; // Importar el archivo de estilos

function Publicartool() {
  const [nombre, setNombre] = useState('');
  const [descripcion, setDescripcion] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    // Aquí puedes enviar los datos del formulario a tu servidor o hacer cualquier otra acción necesaria
    console.log('Nombre:', nombre);
    console.log('Descripción:', descripcion);
    // Luego puedes resetear los campos del formulario si es necesario
    setNombre('');
    setDescripcion('');
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
          <label htmlFor="descripcion">Descripción:</label>
          <textarea
            id="descripcion"
            value={descripcion}
            onChange={(event) => setDescripcion(event.target.value)}
            required
          ></textarea>
        </div>
        <button type="submit">Publicar</button>
      </form>
    </div>
  );
}

export default Publicartool;
