import React, { useState } from 'react';
import './Registro.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
// import Modal from 'react-modal';

function Registro() {
  const [nombre, setNombre] = useState('');
  const [apellidos, setApellidos] = useState('');
  const [correo, setCorreo] = useState('');
  const [username, setUsername] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [confirmarContrasena, setConfirmarContrasena] = useState('');
  const [rol, setRol] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log('Nombre:', nombre);
    console.log('Apellidos:', apellidos);
    console.log('Correo:', correo);
    console.log('Nombre de usuario:', username);
    console.log('Contraseña:', contrasena);
    console.log('Confirmar Contraseña:', confirmarContrasena);
    console.log('Rol:', rol);
    // Aquí puedes agregar lógica para registrar al usuario

    // Verificar si las contraseñas coinciden
    if (contrasena !== confirmarContrasena) {
      console.error('Las contraseñas no coinciden');
      return;
    }

    try {
      // Envía una solicitud al servidor para registrar al usuario
      const ruta = rol === 'propietario' ? 'http://localhost:8443/propietarios' : 'http://localhost:8443/usuarios';
      const response = await axios.post(ruta, {
        nombre,
        apellidos,
        username: correo, // Asumiendo que el correo es el nombre de usuario
        password: contrasena,
        email: correo
      });

      console.log('Registro exitoso:', response.data);
      // Realiza otras acciones necesarias después del registro exitoso, como redirigir al usuario a otra página
      navigate('/inicio');
    } catch { };
    setTimeout(()=>{
      navigate('/');
    },1000);	
  }
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
            <label>Nombre de usuario:</label>
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} className="form-control" />
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
              <option value="propietario">Vendedor</option>
              <option value="usuario">Cliente Regular</option>
            </select>
          </div>
          <button type="submit" className="btn btn-primary">Registrarse</button>
        </form>
      </div>
    );
  }
export default Registro;
