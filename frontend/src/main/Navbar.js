import logo from '../logo.svg';
import '../App.css';
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";


function Navbar() {
    return (
      <nav style={{ marginBottom: '20px' }}>
        <ul style={{ display: 'flex', listStyleType: 'none', padding: 0 }}>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <img src={logo} alt="Logo" style={{ width: '75px', height: '75px', marginRight: '10px' }} /> {/* Cambia la ruta y el nombre del archivo según corresponda */}
            <Link to="/" href="#" style={{ fontSize: '24px', color: 'black' }}>DIY4RENT</Link>
          </li>
          <li style={{ marginTop: '10px', marginLeft: '500px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Buscar</a></li>
          <li style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Publica tu herramienta</a></li>
          <li style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Mis herramientas</a></li>
          <li style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Ayuda</a></li>
          <li style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid black', padding: '5px' }}><a href="#" style={{ color: 'black' }}>Registrarse</a></li>
          <li style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid black', padding: '5px' }}><a href="#" style={{ color: 'black' }}>Iniciar sesión</a></li>
        </ul>
      </nav>
    );
  }


export default Navbar;