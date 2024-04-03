import React, { useState } from 'react';
import './Editartool.css'; // Importa el archivo de estilos para este componente

function Editartool() {
  const [nombre, setNombre] = useState('');
  const [precio, setPrecio] = useState('');
  const [localizacion, setLocalizacion] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [foto, setFoto] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Nombre:', nombre);
    console.log('Precio:', precio);
    console.log('Localización:', localizacion);
    console.log('Descripción:', descripcion);
    console.log('Foto:', foto);
  };

  return (
    <div className="editartool-container">
      <h2>Editar Herramienta</h2>
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
          <label>Localización:</label>
          <input type="text" value={localizacion} onChange={(e) => setLocalizacion(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Descripción:</label>
          <textarea value={descripcion} onChange={(e) => setDescripcion(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Foto:</label>
          <input type="file" accept="image/*" onChange={(e) => setFoto(e.target.files[0])} className="form-control-file" />
        </div>
        <button type="submit" className="btn btn-primary">Guardar</button>
      </form>
    </div>
  );
}

export default Editartool;
