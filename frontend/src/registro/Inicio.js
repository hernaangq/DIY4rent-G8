import React, { useState } from 'react';
import './Inicio.css';
import axios from 'axios'; // Asegúrate de importar axios

function Inicio() {
  const [username, setUsername] = useState('');
  const [contrasena, setContrasena] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log('Correo:', username);
    console.log('Contraseña:', contrasena);
    // Aquí puedes agregar lógica para iniciar sesión

    try {
      // Envía una solicitud al servidor para verificar las credenciales
      const response = await axios.post('http://localhost:8443/login', {
        username,
        contrasena
      });

      // Si la solicitud tiene éxito, podrías manejar el resultado aquí
      console.log('Inicio de sesión exitoso:', response.data);
      
      // Redirige al usuario a una página de inicio de sesión exitosa o realiza otras acciones necesarias
    } catch (error) {
      // Si hay un error, podrías manejarlo aquí (por ejemplo, mostrar un mensaje de error al usuario)
      console.error('Error al iniciar sesión:', error);
    }
  };

  return (
    <div className="inicio-container">
      <h2>Iniciar Sesión</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Nombre de usuario:</label>
          <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} className="form-control" />
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

