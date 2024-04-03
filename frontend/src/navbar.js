
import React from 'react';
import logo from './logo.svg';

function Navbar() {
    return (
      <nav>
        <ul style={{ display: 'flex', listStyleType: 'none', padding: 0 }}>
          <li style={{ display: 'flex', alignItems: 'center', marginRight: '100px' }}>
            <img src={logo} alt="Logo" style={{ width: '75px', height: '75px', marginRight: '10px' }} /> {/* Cambia la ruta y el nombre del archivo según corresponda */}
            <a href="#" style={{ fontSize: '24px', color: 'black' }}>DIY4RENT</a>
          </li>
          <li style={{ marginRight: '100px', marginLeft: '220px' }}><a href="#" style={{ color: 'black' }}>Buscar</a></li>
          <li style={{ marginRight: '100px' }}><a href="#" style={{ color: 'black' }}>Publica tu herramienta</a></li>
          <Link style={{ marginRight: '100px' }}><a href="#" style={{ color: 'black' }}>Mis herramientas</a></Link>
          <li style={{ marginRight: '100px' }}><a href="#" style={{ color: 'black' }}>Ayuda</a></li>
          <li style={{ borderRadius: '10px', border: '2px solid black', padding: '5px' }}><a href="#" style={{ color: 'black' }}>hernaangq</a></li>
        </ul>
      </nav>
    );
  }
  export default Navbar;