import React, { useState } from 'react';
import './Registro.css';

function Registro() {
  const [nombre, setNombre] = useState('');
  const [apellidos, setApellidos] = useState('');
  const [correo, setCorreo] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [confirmarContrasena, setConfirmarContrasena] = useState('');
  const [rol, setRol] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Nombre:', nombre);
    console.log('Apellidos:', apellidos);
    console.log('Correo:', correo);
    console.log('Contraseña:', contrasena);
    console.log('Confirmar Contraseña:', confirmarContrasena);
    console.log('Rol:', rol);
    // Aquí puedes agregar lógica para registrar al usuario
  };

  return (
    <div className="registro-container">
      <h2>Registro</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Nombre:</label>
          <input type="text" value={nombre} onChange={(e) => setNombre(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Apellidos:</label>
          <input type="text" value={apellidos} onChange={(e) => setApellidos(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Correo:</label>
          <input type="email" value={correo} onChange={(e) => setCorreo(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Contraseña:</label>
          <input type="password" value={contrasena} onChange={(e) => setContrasena(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Confirmar Contraseña:</label>
          <input type="password" value={confirmarContrasena} onChange={(e) => setConfirmarContrasena(e.target.value)} className="form-control" />
        </div>
        <div className="form-group">
          <label>Rol:</label>
          <select value={rol} onChange={(e) => setRol(e.target.value)} className="form-control">
            <option value="">Selecciona un rol</option>
            <option value="vendedor">Vendedor</option>
            <option value="cliente">Cliente Regular</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Registrarse</button>
      </form>
    </div>
  );
}

export default Registro;
