# GearRent Pro

## Project Description

**GearRent Pro** is a comprehensive equipment rental management system built with JavaFX. The application provides a robust solution for managing equipment rentals, branches, categories, and user operations across different organizational levels.

### Key Features

- **Multi-level User Management**: Support for Admin, Branch Manager, and Staff roles with role-based access control
- **Equipment Management**: Track and manage equipment inventory, availability, and status
- **Branch Management**: Handle multiple branch locations and their operations
- **Category Management**: Organize equipment into categories for easier management
- **Authentication & Authorization**: Secure login system with session management
- **Intuitive UI**: Modern JavaFX-based user interface for seamless user experience

### Technology Stack

- **Frontend**: JavaFX (FXML)
- **Backend**: Java
- **Database**: MySQL
- **Build Tool**: Apache Ant
- **Architecture**: MVC (Model-View-Controller)

---

## Database Configuration

### Prerequisites

1. **MySQL Server**: Install MySQL Server (version 8.0 or higher recommended)
2. **MySQL Connector/J**: JDBC driver for MySQL (should be included in the project's lib folder)

### Setup Instructions

1. **Create the Database**
   ```sql
   CREATE DATABASE gearrentpro;
   ```

2. **Configure Database Connection**
   
   Update the database credentials in the `DBConnection.java` file located at:
   ```
   src/com/gearrentPro/db/DBConnection.java
   ```
   
   Modify the following parameters according to your MySQL configuration:
   - **Database URL**: `jdbc:mysql://localhost:3306/gearrentpro`
   - **Username**: Your MySQL username
   - **Password**: Your MySQL password

3. **Create Database Tables**
   
   Run the SQL scripts to create the necessary tables for:
   - Users (system_user)
   - Branches
   - Categories
   - Equipment
   - Equipment Status tracking

   *Note: Ensure all tables are created with appropriate foreign key relationships and constraints.*

4. **Seed Initial Data**
   
   Insert default administrator and test user accounts to access the system.

---

## How to Run the Application

### Method 1: Using NetBeans IDE (Recommended)

1. **Open the Project**
   - Launch NetBeans IDE
   - Go to `File` → `Open Project`
   - Navigate to the project directory and select it

2. **Clean and Build**
   - Right-click on the project name
   - Select `Clean and Build`

3. **Run the Application**
   - Right-click on the project name
   - Select `Run`
   - The application will launch with the Login screen

### Method 2: Using Command Line with Ant

1. **Navigate to Project Directory**
   ```bash
   cd "e:\IJSE Java Course\GearRent Pro"
   ```

2. **Build the Project**
   ```bash
   ant clean
   ant build
   ```

3. **Run the Application**
   ```bash
   ant run
   ```

### Method 3: Using JAR File

1. **Build JAR**
   ```bash
   ant jar
   ```

2. **Run JAR**
   ```bash
   java -jar dist/GearRentPro.jar
   ```

---

## Default Login Credentials

The system includes three user roles with different access levels. After setting up the database, you should create default users for each role:

### 1. Admin
- **Role**: Full system access, user management, and system configuration
- **Username**: admin
- **Password**: admin123
- **Permissions**: 
  - Manage all users
  - Access all branches
  - Full equipment management
  - System settings

### 2. Branch Manager
- **Role**: Branch-level management and operations
- **Username**: manager_pan
- **Password**: manager123
- **Permissions**: 
  - Manage branch staff
  - Branch-specific equipment management
  - View branch reports

### 3. Staff
- **Role**: Day-to-day operations and customer service
- **Username**: staff_pan
- **Password**: staff123
- **Permissions**: 
  - Process rentals
  - Update equipment status
  - View equipment inventory

> **Security Note**: It is highly recommended to change all default credentials immediately after the first login. Store credentials securely and follow your organization's password policy.

---

## Project Structure

```
GearRent Pro/
├── src/
│   ├── com/gearrentPro/
│   │   ├── Main.java                    # Application entry point
│   │   ├── auth/                        # Authentication & session management
│   │   ├── controller/                  # JavaFX controllers
│   │   ├── dao/                         # Data Access Objects
│   │   ├── db/                          # Database connection
│   │   ├── dto/                         # Data Transfer Objects
│   │   ├── entity/                      # Domain entities
│   │   ├── service/                     # Business logic
│   │   └── view/                        # FXML view files
│   └── util/                            # Utility classes
├── build.xml                            # Ant build configuration
└── README.md                            # This file
```

---

## Troubleshooting

### Database Connection Issues
- Verify MySQL server is running
- Check database credentials in `DBConnection.java`
- Ensure MySQL Connector/J is in the classpath
- Confirm database `gearrentpro` exists

### JavaFX Runtime Issues
- Ensure JavaFX libraries are properly configured
- Check Java version compatibility (Java 8+ with JavaFX support)

### Build Errors
- Verify all dependencies are available
- Clean and rebuild the project
- Check `build.xml` configuration

---

## Support & Contribution

For issues, suggestions, or contributions, please contact the development team or create an issue in the project repository.

---

## License

This project is developed for educational purposes as part of the IJSE Java Course.

---

**Version**: 1.0  
**Last Updated**: January 2026
