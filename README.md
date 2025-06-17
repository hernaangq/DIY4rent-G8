# DIY4rent: DIY Tool Marketplace

A full-stack web application built as the final ISST project at ETSIT (UPM), offering a platform to list, rent, and review DIY tools.

---

## 🔧 Tech Stack

- **Backend**: Spring Boot (Java), built with Maven  
- **Frontend**: React (JavaScript)  
- **Communication**: RESTful APIs between frontend and backend  
- **Security**: HTTPS support via JKS keystore  
- **Development**: HTTP tool for API testing (`get.http`)  

---

## ⚙️ Project Structure

```

DIY4rent-G8/
├── Backend/
│   ├── src/main/java/...      # Spring Boot application, controllers, services, repos, entities
│   ├── src/main/resources/    # application settings, keystore (mykeys.jks)
│   └── pom.xml                # Maven build file, dependencies
├── frontend/
│   ├── public/                # HTML template, static assets
│   ├── src/                   # React components, pages, CSS
│   └── package.json           # React dependencies and scripts
├── get.http                   # API request definitions for backend testing
└── README.md                  # This file

````

---

## 🚀 Running the App

### 1. Backend  
```bash
cd Backend
mvn clean install
mvn spring-boot:run
````

* The server runs by default on `https://localhost:8443` (uses `mykeys.jks` for HTTPS).

### 2. Frontend

```bash
cd frontend
npm install
npm start
```

* This launches the React development server at `http://localhost:3000`, configured to interact with the backend.

---

## 📸 Screenshots

![Screenshot 2024-05-05 191822](https://github.com/user-attachments/assets/794b2af9-cd6b-4190-b6e0-108b88edf204)
![Screenshot 2024-05-05 191230](https://github.com/user-attachments/assets/b28d1a30-3a8b-4b9a-9613-eddc962e7b71)
![Screenshot 2024-05-05 191141](https://github.com/user-attachments/assets/6415364b-e4ef-4a04-805d-12e67879f4be)

---

## 🛠️ Features

* User registration and login
* Listings for DIY tools: create, view, edit, delete
* Search functionality and filtering
* Rental workflow: request, approve, and review tools
* Responsive UI built with React

---

## 🎓 About the Project

This was my final-year ISST (Ingeniería de Servicios y Sistemas de Telecomunicación) project at ETSIT (UPM), showcasing my ability to design, implement, and deploy a complete full-stack application with modern technologies and real-world development practices.

---

## 📫 Contact

Feel free to reach out for questions or collaboration:

* 📧 Email: [hernangarqui@gmail.com](mailto:hernangarqui@gmail.com)
* 💼 LinkedIn: [Hernán García Quijano](https://www.linkedin.com/in/hernan-garcia-quijano)

```


