# 📚 NoteDrop - Notes Sharing Platform

[![Website](https://img.shields.io/website?url=https%3A%2F%2Fnotedrop-wlm0.onrender.com&label=Visit%20NoteDrop&style=for-the-badge)](https://notedrop-wlm0.onrender.com)

**Live Website:** [https://notedrop-wlm0.onrender.com](https://notedrop-wlm0.onrender.com)

---

NoteDrop is a web application that allows students to upload, share, and discover educational notes across various subjects.  
Built with **Spring Boot** for the backend and **HTML/CSS/Thymeleaf** for the frontend.

---

## ✨ Features

- 🔒 User authentication (sign up/login)
- 📄 Upload and share notes with metadata (subject, topic, description)
- 🔍 Search and browse notes by subject or keywords
- 👤 User profiles with published notes
- ➕ Follow other users
- 📚 Save favorite notes

---

## 🛠️ Technology Stack

**Backend:**
- Spring Boot (Java)
- Spring Security for authentication
- Thymeleaf for server-side templating

**Frontend:**
- HTML5
- CSS3
- JavaScript

**Database and Storage:**
- MongoDB for data storage
- Supabase for file storage

**Deployment:**
- Render (for hosting the backend and frontend)

---

## 📂 Project Structure

```bash
NoteDrop/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── NoteDrop/
│       │               ├── config/
│       │               │   └── SecurityConfig.java
│       │               ├── controller/
│       │               │   └── (All controller files)
│       │               ├── dto/
│       │               │   └── (All DTO files)
│       │               ├── entity/
│       │               │   └── (All entity files)
│       │               ├── repo/
│       │               │   └── (All repository interfaces)
│       │               └── service/
│       │                   ├── IMPL/
│       │                   │   └── (Service implementation files)
│       │                   └── (Service interface files)
│       └── resources/
│           ├── static/
│           │   └── css/
│           │       └── (CSS files)
│           ├── templates/
│           │   └── (HTML files)
│           └── application.properties
├── Dockerfile
├── pom.xml
├── .env
```

## 📸 Screenshots

### Welcome Page
<img src="https://github.com/user-attachments/assets/0b9fe8c7-e875-4529-ad59-ed75bd15f489" width="600"/>

### Note Upload Page
<img src="https://github.com/user-attachments/assets/590ed631-2243-493f-ace9-1ca833f72cb6" width="600"/>

### User Profile Page
<img src="https://github.com/user-attachments/assets/24328650-9f52-4731-b814-12c566e04e69" width="600"/>

### Search Results
<img src="https://github.com/user-attachments/assets/c513ab62-d348-478b-a5ba-20025ed2fe50" width="600"/>

---

## 🚀 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Shadow-Gard3n/NoteDrop.git
   cd NoteDrop
   
