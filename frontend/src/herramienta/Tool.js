import React from 'react';
import './Tool.css'; // Importa el archivo de estilos para Tool
import toolImage from '../images/martillo.jpg'; // Importa la imagen de la herramienta

const Tool = () => {
  return (
    <div className="container">
      <div className="tool-image" style={{ width: '25vw' }}>
        <img src={toolImage} alt="Tool Image" style={{ width: '100%', height: 'auto' }} />
      </div>
      <div className="tool-details">
        <div className="tool-info">
          <h2>Tool Name</h2>
          <p>Manufacturer: XYZ Corp</p>
          <p>Model: ABC123</p>
          <p>Year: 2023</p>
        </div>
        <div className="tool-description">
          <h3>Description</h3>
          <p>
            This is a description of the tool. It is very useful for various tasks
            and has a durable design.
          </p>
        </div>
        <div className="ratings">
          <h3>Ratings</h3>
          <p>⭐⭐⭐⭐⭐ (5 stars)</p>
          <p>Rated by 100 users</p>
        </div>
        <div className="location">
          <h3>Available Locations</h3>
          <p>Location 1</p>
          <p>Location 2</p>
          <p>Location 3</p>
        </div>
      </div>
    </div>
  );
};

export default Tool;
