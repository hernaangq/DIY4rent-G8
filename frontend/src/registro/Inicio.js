import React, { useState } from 'react';
import './Inicio.css';

function Inicio() {
  const [correo, setCorreo] = useState('');
  const [contrasena, setContrasena] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Correo:', correo);
    console.log('Contraseña:', contrasena);
    // Aquí puedes agregar lógica para iniciar sesión
  };

  return (
    <div className="inicio-container">
      <h2>Iniciar Sesión</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Correo:</label>
          <input type="email" value={correo} onChange={(e) => setCorreo(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Contraseña:</label>
          <input type="password" value={contrasena} onChange={(e) => setContrasena(e.target.value)} className="form-control" />
        </div>
        <button type="submit" className="btn btn-primary">Iniciar Sesión</button>
      </form>
    </div>
  );
}

export default Inicio;
