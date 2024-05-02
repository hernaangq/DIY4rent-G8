import React, { useEffect, useState } from "react";
import logo from "./logo.svg";
import "./App.css";
import Navbar from "./main/Navbar";
import ToolList from "./main/ToolList";
import Tool from "./herramienta/Tool";
import Mytools from "./herramienta/Mytools";
import Editartool from "./herramienta/Editartool"
import Inicio from "./registro/Inicio"
import Registro from "./registro/Registro"
import Publicartool from "./herramienta/Publicartool";
import Ayuda from "./main/Ayuda";
import axios from 'axios';
import Myalquiler from "./herramienta/Myalquiler";
import Valorar from "./herramienta/Valorar";
import insta from "../src/images/insta2.png"; 
import twitter from "../src/images/x2.png"; 
import facebook from "../src/images/facebook2.png";
import mono from "../src/images/mono2.png";


import { BrowserRouter as Router, Route, Link, Routes} from "react-router-dom";
import Footer from "./main/Footer";
// import { set } from "@citation-js/core/lib/Cite/set";


function App() {

  const [items, setItems] = useState([]);
  const [itemsPropietario, setItemsPropietario] = useState([]);
  const [itemsAlquiladas, setItemsAlquiladas] = useState([]);
  const [filteredData, setFilteredData] = useState([])

  useEffect(() => {
    callServer();
    callServer2();
    callServer3();
  }, []);

  let response;
  let response2;
  let id = 1; // Cambiar por el id del usuario actual
  const jwt = localStorage.getItem('token')
  const callServer = async () => {
    try {
      response = await axios.get('https://localhost:8443/herramientas',
        //{
        // headers: {
        //   'Authorization': `Bearer ${jwt}`
        // }
      );
      setItems(response.data.filter(item => !item.estaAlquilada));
      setItemsPropietario(response2.data);
    } catch (error) { }

  }

  const callServer2 = async () => {
    try {
      if (jwt) {
      let username = localStorage.getItem('nombreUsuario');
      response2 = await axios.get('https://localhost:8443/herramientas/propietario/' + username);
        // {
        //   headers: {
        //     'Authorization': `Bearer ${jwt}`
        //   }
      setItemsPropietario(response2.data);}
    } catch (error) { }
  }


  const callServer3 = async () => {
    try {
      if (jwt) {
      let username = localStorage.getItem('nombreUsuario');
      response2 = await axios.get('https://localhost:8443/alquileres/usuario/' + username);
        // {
        //   headers: {
        //     'Authorization': `Bearer ${jwt}`
        //   }
      setItemsAlquiladas(response2.data);}
    } catch (error) { }
  }

// const handleFilterChange = (filteredResults) => {
//   setFilteredData(filteredResults);
  // };

  function handleFilterChange(items, palabra) {
  let res = [];
      res = (items.filter((producto) =>
       producto.nombre.toLowerCase().includes(palabra.toLowerCase())));
  setFilteredData(res);
}


  return (
    <Router>
      <div className="App">
        <Navbar herramientas={items} onFilterChange={handleFilterChange} />
        <Routes>
          <Route path="/" element={<ToolList herramientas={(filteredData.length > 0) ? filteredData : items}  />} />
          <Route path="/herramientas/:rutaId" element={<Tool herramientas = {items} />} />
          <Route path="/herramientas-propias/:rutaId" element={<Tool herramientas = {itemsPropietario} />} />
          {itemsPropietario.length > 0 && (
          <Route path="/tool/editar/:rutaId" element={<Editartool herramientas = {itemsPropietario} />} />)}
          <Route path="/misherramientas/:rutaId" element={<Mytools herramientas={itemsPropietario} />} />
          <Route path="/misalquileres/:rutaId" element={<Myalquiler herramientas={itemsAlquiladas} />} />
          <Route path="/iniciar" element={<Inicio />} />
          <Route path="/valorar/:rutaId" element={<Valorar herramientas = {items} alquileres={itemsAlquiladas} />} /> 
          <Route path="/registrar" element={<Registro />} />
          <Route path="/publicar" element={<Publicartool/>} />
          <Route path="/ayuda" element={<Ayuda/>}/>
        </Routes>

        <footer className="footer">
          <p style={{ color: 'white' }}>¡Gracias por visitar nuestra página! <i>DIY4rent © 2024 Copyright</i></p>
          <div>
            <a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer">
              <img src={facebook} alt="Facebook" width="30" height="30" />
            </a>
            <a href="https://www.twitter.com" target="_blank" rel="noopener noreferrer">
              <img src={twitter} alt="Twitter" width="30" height="30" />
            </a>
            <a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer">
              <img src={insta} alt="Instagram" width="30" height="30" />
            </a>
            
          </div>
        </footer>
        
      </div>
    </Router>
  );
}

export default App;
