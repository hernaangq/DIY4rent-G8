import logo from '../images/logo1ejemplov.png';
import '../App.css';
import './Navbar.css'
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import { useEffect, useState } from 'react';
import axios from 'axios';


function Navbar(props) {
  const [input, setInput] = useState('');
  const [items, setItems] = useState([]);

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
          <button  onClick={handleClick} style={{ backgroundColor: '#606C38', display: 'flex', alignItems: 'center',  }}><strong style={{ color: 'white', textDecoration: 'none' }}>Buscar</strong></button>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', marginBottom: '20px'}}>
        <Link className="letras" to="/publicar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Publica tu herramienta</strong></a></Link>
        <Link className="letras" to={"/misherramientas/" + props.propietarioId} href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white' , textDecoration: 'none'}}>Mis herramientas</strong></a></Link>
        <Link className="letras" to="/ayuda" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white' , textDecoration: 'none'}}>Ayuda</strong></a></Link>
        <Link className="letras" to="/registrar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Registrarse</strong></a></Link>
        <Link className="letras" to="/iniciar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px', fontSize: '16px', textDecoration: 'none' }}><a className="letras" href="#"><strong style={{ color: 'white', textDecoration: 'none' }}>Iniciar sesión</strong></a></Link>
        </div>
      </ul>
    </nav>
  );
}


export default Navbar;