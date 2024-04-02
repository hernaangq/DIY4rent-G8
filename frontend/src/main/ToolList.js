import logo from "../logo.svg";
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import Tool from "../herramienta/Tool";


function ToolList() {
  const tools = [
    { name: "Herramienta1", owner: "Propietario1", src: logo },
    { name: "Herramienta2", owner: "Propietario2", src: logo },
    { name: "Herramienta3", owner: "Propietario3" },
    { name: "Herramienta4", owner: "Propietario4" },
    { name: "Herramienta5", owner: "Propietario5" },
    { name: "Herramienta6", owner: "Propietario6" },
    { name: "Herramienta7", owner: "Propietario7" },
    { name: "Herramienta8", owner: "Propietario8" },
    { name: "Herramienta9", owner: "Propietario9" },
    { name: "Herramienta10", owner: "Propietario10" },
    { name: "Herramienta11", owner: "Propietario11" },
    { name: "Herramienta12", owner: "Propietario12" },
  ];

  return (
    <Router>
      <div className="ToolList">
        {tools.map((tool, index) => (
          <div className="ToolList-item" key={index}>
            <img src={tool.src} alt={tool.name} />
            <div>{tool.owner}</div>
            <div>{tool.name}</div>
            <Link to="/tool">Link a la herramienta</Link>
          </div>
        ))}
      </div>
      <Routes>
        <Route path="/tool" element={<Tool />} />
      </Routes>
          {" "}
    </Router>
  );
}

export default ToolList;
