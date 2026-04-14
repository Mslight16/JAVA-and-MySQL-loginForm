# 🔐 Java Login System with MySQL

## 📌 Overview

This is a Java Swing-based Login System connected to a MySQL database using JDBC. It authenticates users and displays their details on a dashboard after successful login.

---

## 🛠️ Technologies Used

* Java (Swing for GUI)
* MySQL Database
* JDBC (MySQL Connector/J)
* Visual Studio Code

---

## ⚙️ Setup Instructions

### 1️⃣ Install Requirements

* Install Java (JDK 8 or above)
* Install MySQL Server
* Install VS Code

---

### 2️⃣ Create Database

Open MySQL and run:

```sql
CREATE DATABASE loginusers;
USE loginusers;

CREATE TABLE users (
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255)
);
```

---

### 3️⃣ Insert Sample Data
Insert dummy data into your database table

---

### 4️⃣ Add MySQL Connector (IMPORTANT)

1. Download MySQL Connector/J (.jar file)
2. Create a folder:

```
lib/
```

3. Place the `.jar` file inside it

---

### 5️⃣ Compile & Run Project

Open terminal inside `src` folder:

```bash
javac -cp ".;../lib/mysql-connector-j-8.0.xx.jar" LoginForm.java
java -cp ".;../lib/mysql-connector-j-8.0.xx.jar" LoginForm
```

---

### 6️⃣ Configure Database Credentials

Update this in your code:

```java
final String DB_URL = "jdbc:mysql://localhost:3306/loginusers";
final String USERNAME = "root";
final String PASSWORD = "your_password_here";
```

⚠️ Note: Password is not included in this repository for security reasons.

---

## 🚀 Features

* User Login Authentication
* MySQL Database Integration
* Dashboard with User Details
* Clean Swing UI

---


