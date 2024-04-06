import logo from '../images/logo1ejemplov.png';
import '../App.css';
import './Navbar.css'
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import { useEffect, useState } from 'react';
import axios from 'axios';


function Navbar(props) {


const [input, setInput] = useState('');
const [items, setItems] = useState([]);

  
    // const [lista, setLista] = useState(response.data);
    
    function filtrarInput(items, palabra) {
      let res = [];
          res = items.filter((producto) =>
           producto.nombre.toLowerCase().includes(palabra.toLowerCase()));
      return res;
    }

    return (
      <nav className="navbar" style={{ marginBottom: '20px' }}>
        <ul style={{ display: 'flex', listStyleType: 'none', padding: 0 }}>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <img src={logo} alt="Logo" style={{ width: '75px', height: '75px', marginRight: '10px' }} /> {/* Cambia la ruta y el nombre del archivo según corresponda */}
            <Link to="/" href="#" style={{ fontSize: '24px', color: 'black' }}>DIY4RENT</Link>
          </li>
          <div>
          <input id="filtro" onChange={e => setInput(e.target.value)}></input>
          <button onClick={() => setItems(filtrarInput(props.herramientas, input))} style={{ marginTop: '10px', marginLeft: '500px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Buscar</a></button>
          </div>
          <Link to="/publicar" href="#" style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Publica tu herramienta</a></Link>
          <Link to="/mytools" href="#" style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Mis herramientas</a></Link>
          <Link to="/ayuda" href="#" style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Ayuda</a></Link>
          <Link to ="/registrar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid black', padding: '5px' }}><a href="#" style={{ color: 'black' }}>Registrarse</a></Link>
          <Link to="/iniciar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid black', padding: '5px' }}><a href="#" style={{ color: 'black' }}>Iniciar sesión</a></Link>
        </ul>
        
      </nav>
      
    );
  }


export default Navbar;