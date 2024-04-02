import logo1 from "../images/martillo.jpg";
import logo2 from "../images/sierra.jpg";
import logo3 from "../images/toolbasic.jpg";
import "../App.css";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import Tool from "../herramienta/Tool";
import './ToolList.css';


function ToolList() {
  const tools = [
    { name: "Herramienta1", owner: "Propietario1", src: logo1 },
    { name: "Herramienta2", owner: "Propietario2", src: logo2 },
    { name: "Herramienta3", owner: "Propietario3", src: logo3},
    { name: "Herramienta4", owner: "Propietario4", src: logo3 },
    { name: "Herramienta5", owner: "Propietario5", src: logo3 },
    { name: "Herramienta6", owner: "Propietario6", src: logo3 },
    { name: "Herramienta7", owner: "Propietario7", src: logo3 },
    { name: "Herramienta8", owner: "Propietario8", src: logo3 },
    { name: "Herramienta9", owner: "Propietario9", src: logo3 },
    { name: "Herramienta10", owner: "Propietario10", src: logo3 },
    { name: "Herramienta11", owner: "Propietario11", src: logo3 },
    { name: "Herramienta12", owner: "Propietario12", src: logo3 },
  ];

  return (
      <div className="ToolList">
        {tools.map((tool, index) => (
          <div className="ToolList-item" key={index}>
            <img src={tool.src} alt={tool.name} />
            <div>{tool.owner}</div>
            <div>{tool.name}</div>
            <Link to="/tool/1">Link a la herramienta</Link>
          </div>
        ))}
      </div>
  );
}

export default ToolList;
