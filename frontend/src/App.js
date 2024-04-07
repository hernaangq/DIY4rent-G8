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

import { BrowserRouter as Router, Route, Link, Routes} from "react-router-dom";

function App() {

  const [items, setItems] = useState([]);
  const [filteredData, setFilteredData] = useState([])

  useEffect(() => {
    callServer()
  }, []);

  let response;
  const callServer = async () => {

    response = await axios.get('http://localhost:8443/herramientas');
    // const datos = await response.json();
    setItems(response.data.filter(item => !item.estaAlquilada));
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
        <Navbar herramientas={items} onFilterChange={handleFilterChange}/>
        <Routes>
          <Route path="/" element={<ToolList herramientas={(filteredData.length > 0) ? filteredData : items}  />} />
          <Route path="/herramientas/:rutaId" element={<Tool herramientas = {items} />} />
          <Route path="/tool/:id/editar" element={<Editartool />} />
          <Route path="/mytools" element={<Mytools />} />
          <Route path="/iniciar" element={<Inicio />} />
          <Route path="/registrar" element={<Registro />} />
          <Route path="/publicar" element={<Publicartool/>} />
          <Route path="/ayuda" element={<Ayuda/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
