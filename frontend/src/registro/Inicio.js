import React, { useState } from 'react';
import './Inicio.css';
import axios from 'axios'; // Asegúrate de importar axios
import { useNavigate } from 'react-router-dom';

function Inicio() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [usernameProp, setUsernameProp] = useState('');
  const [passwordProp, setPasswordProp] = useState('');
  const [logged, setLogged] = useState(false);
  const navigate = useNavigate();

  const handleSubmitUsuario = async (event) => {
    event.preventDefault();

    try {
        const response = await axios.post('https://localhost:8443/auth/login', {
            username: username,
            password: password
        });

        const token = response.data.token;
        localStorage.setItem('token', token);
        const rol = response.data.authorities[0] ? response.data.authorities[0].authority : '';
        localStorage.setItem('rol', rol);
        localStorage.setItem('nombreUsuario', response.data.username);

        // Set the token in the default headers for subsequent requests
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

        let id;
        try {
            const response2 = await axios.get('https://localhost:8443/auth/usuarioName/' + response.data.username);
            if (response2.data) {
                id = response2.data.id;
            }
        } catch (error) {
            console.error('Error getting usuarioName:', error);
        }

        console.log('Inicio de sesión exitoso:', response.data);
        navigate('/');
        window.location.reload();
    } catch (error) {
        console.error('Error al iniciar sesión:', error);
    }
};

const handleSubmitPropietario = async (event) => {
  event.preventDefault();

  try {
      const response = await axios.post('https://localhost:8443/auth/loginPropietario', {
          username: usernameProp,
          password: passwordProp
      });
      const token = response.data.token;
      console.log(token);
      localStorage.setItem('token', token);
      const rol = response.data.authorities[0] ? response.data.authorities[0].authority : '';
      localStorage.setItem('rol', rol);
      localStorage.setItem('nombreUsuario', response.data.username);
      // console.log(response.data);
      // console.log(response.data.authorities);
      // console.log(response.data.authorities[0]);
      // console.log(response.data.authorities[0].authority);

      // Set the token in the default headers for subsequent requests
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

      let id;
      try {
        console.log(response.data.username);
        const response2 = await axios.get('https://localhost:8443/propietariosName/' + response.data.username);
        if (response2.data) {
          localStorage.setItem('id', response2.data.id);
          console.log(response2.data.id);
          id = response2.data.id;
        }
      } catch (error) {
          console.error('Error getting usuarioName:', error);
      }

      console.log('Inicio de sesión exitoso:', response.data);
      navigate('/');
      window.location.reload();
  } catch (error) {
    console.log(usernameProp, passwordProp)
      console.error('Error al iniciar sesión:', error);
  }
};
//  style={{display: 'flex'}}
  return (
    <div style={{display: 'flex'}}>
      <div className="inicio-container" >
        <h2>Iniciar Sesión</h2>
        <div className="login-forms-container">
          <div className="form-box">
            <form onSubmit={handleSubmitUsuario}>
              <div className="form-group">
                <label>Nombre de usuario:</label>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} className="form-control" />
              </div>
              <div className="form-group">
                <label>Contraseña:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="form-control" />
              </div>
              <button type="submit"  className="btn btn-primary">Iniciar Sesión</button>
            </form>
          </div>
        </div>
      </div>
      <div className="inicio-container">
        <div className="form-box">
        <h2>¿Eres propietario?</h2>
          <form onSubmit={handleSubmitPropietario}>
            <div className="form-group">
              <label>Nombre de usuario:</label>
              <input type="text" value={usernameProp} onChange={(e) => setUsernameProp(e.target.value)} className="form-control" />
            </div>
            <div className="form-group">
              <label>Contraseña:</label>
              <input type="password" value={passwordProp} onChange={(e) => setPasswordProp(e.target.value)} className="form-control" />
            </div>
            <button type="submit" className="btn btn-primary">Iniciar Sesión</button>
          </form>
        </div>
      </div>

    </div>
  );
}

export default Inicio;

