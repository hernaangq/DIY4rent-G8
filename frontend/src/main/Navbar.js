import logo from '../images/logo1ejemplov.png';
import '../App.css';
import './Navbar.css'
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';


async function getRole() {
  return await localStorage.getItem('rol');
}

function Navbar(props) {
  const [input, setInput] = useState('');
  const [items, setItems] = useState([]);
  const navigate = useNavigate();

  // const [rol, setRol] = useState('');
  // console.log(rol);

  const [loggedOut, setLoggedOut] = useState(false);
  const jwt = localStorage.getItem('token')
  const rol = localStorage.getItem('rol')
  const id = localStorage.getItem('id')
  const username = localStorage.getItem('nombreUsuario')

  // useEffect(() => {
  //   getRole().then((role) => {
  //     setRol(role);
  //   });
  // }, []);


const handleLogout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('rol');
  localStorage.removeItem('id');
  localStorage.removeItem('nombreUsuario');
  setLoggedOut(true);
  navigate('/');
  window.location.reload();
}

const [isPropietario, setIsPropietario] = useState(false);
const [isUsuario, setIsUsuario] = useState(false);

useEffect(() => {
  const checkIsPropietario = async () => {
    try {
      const response = await axios.get('https://localhost:8443/propietarios/isPropietario/' + username);
      setIsPropietario(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const checkIsUsuario = async () => {
    try {
      const response = await axios.get('https://localhost:8443/usuarios/isUsuario/' + username);
      setIsUsuario(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  checkIsUsuario();
  checkIsPropietario();
}, []);

  useEffect(() => {
    handleClick();
  }, []);

  
  function handleClick() {
    props.onFilterChange(props.herramientas, input);
  }

  return (
    <nav className="navbar" style={{ marginBottom: '20px' }}>
      <ul style={{ display: 'flex', listStyleType: 'none', padding: 0 }}>
        <li style={{ display: 'flex', alignItems: 'center' }}>
          <Link to="/" href="#"><img src={logo} alt="Logo" style={{ width: '75px', textDecoration: 'none', height: '75px', marginRight: '10px' }} /></Link>
          <Link className="letras" to="/" href="#" style={{ fontSize: '24px', textDecoration: 'none' }}><strong style={{ color: 'white', textDecoration: 'none' }}>DIY4RENT</strong></Link>
        </li>
        <div style={{ display: 'flex', alignItems: 'center', marginLeft: '20px' }}>
          <input id="filtro" onChange={e => setInput(e.target.value)} style={{ marginRight: '10px', height: '30px', fontSize: '18px' }}></input>
          <button onClick={handleClick} style={{ backgroundColor: '#606C38', display: 'flex', alignItems: 'center', }}><strong style={{ color: 'white', textDecoration: 'none' }}>Buscar</strong></button>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', marginBottom: '20px' }}>
          {isPropietario && <Link className="letras" to="/publicar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Publica tu herramienta</strong></a></Link>}
          {isPropietario && <Link className="letras" to={"/misherramientas/" + username} href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Mis herramientas</strong></a></Link>}
          {(isUsuario && !isPropietario) && <Link className="letras" to={"/misalquileres/" + username} href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white' , textDecoration: 'none'}}>Mis alquileres</strong></a></Link>}
        <Link className="letras" to="/ayuda" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Ayuda</strong></a></Link>
        {jwt ? <div>
        <Link className="letras" to="/" onClick={handleLogout} href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}>
          <strong style={{ color: 'white', textDecoration: 'none' }}>{username} | Cerrar Sesión</strong>
        </Link>
        </div> :
              <div style={{display: 'flex', alignItems: 'center'}}><Link className="letras" to="/registrar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Registrarse</strong></a></Link>
                <Link className="letras" to="/iniciar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Iniciar sesión</strong></a></Link>
              </div>}
          </div>
      </ul>
    </nav>
  );
}


export default Navbar;