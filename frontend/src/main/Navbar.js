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
    
    // function filtrarInput(items, palabra) {
    //   let res = [];
    //       res = (items.filter((producto) =>
    //        producto.nombre.toLowerCase().includes(palabra.toLowerCase())));
    //   props.onFilterChange(res);
    // }

    useEffect(() => {
      handleClick()
    }, []);

    function handleClick (){
      props.onFilterChange(props.herramientas, input);
    }

    return (
      <nav className="navbar" style={{ marginBottom: '20px' }}>
        <ul style={{ display: 'flex', listStyleType: 'none', padding: 0 }}>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <img src={logo} alt="Logo" style={{ width: '75px', height: '75px', marginRight: '10px' }} /> {/* Cambia la ruta y el nombre del archivo según corresponda */}
            <Link className="letras" to="/" href="#" style={{ fontSize: '24px'}}>DIY4RENT</Link>
          </li>
          <div>
            <input id="filtro" onChange={e => setInput(e.target.value)}></input>
            <button onClick={handleClick} style={{ marginTop: '10px', backgroundColor: '#606C38', marginLeft: '500px', display: 'flex', alignItems: 'center' }}><a href="#" style={{ color: 'black' }}>Buscar</a></button>
          </div>
          <Link className="letras" to="/publicar" href="#" style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a className="letras" href="#" >Publica tu herramienta</a></Link>
          <Link className="letras" to="/mytools" href="#" style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a className="letras" href="#" >Mis herramientas</a></Link>
          <Link className="letras" to="/ayuda" href="#" style={{ marginTop: '10px', marginLeft: '50px', display: 'flex', alignItems: 'center' }}><a className="letras" href="#" >Ayuda</a></Link>
          <Link className="letras" to ="/registrar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px' }}><a className="letras" href="#" >Registrarse</a></Link>
          <Link className="letras" to="/iniciar" href="#" style={{ marginTop: '20px', marginLeft: '50px', display: 'flex', alignItems: 'center', borderRadius: '10px', border: '2px solid #FEFAE0', padding: '5px' }}><a className="letras" href="#" >Iniciar sesión</a></Link>
        </ul>
      </nav>
      
    );
  }


export default Navbar;