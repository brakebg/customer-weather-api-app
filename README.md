# Customer Service API

A Spring Boot application that manages customer information and their location preferences with temperature conditions.

## Technology Stack

- Spring Boot (Java 17)
- PostgreSQL
- RESTful API
- AWS (for deployment)

## Features

### API Endpoints

1. Create Customer Information (`POST /api/customers`)
   - Accept customer details including name, email, and locations
   - Each location includes city, country, and temperature condition

2. Retrieve Customer Information (`GET /api/customers`)
   - Fetch all customers with their associated locations and temperature conditions

## Database Schema

### Customer Table
- id (Primary Key)
- name
- email

### Location Table
- id (Primary Key)
- city
- country
- temperature_condition
- customer_id (Foreign Key)

## Setup Instructions

1. Prerequisites
   - Java 17 or higher
   - Maven
   - PostgreSQL
   - AWS CLI (for deployment)

2. Database Setup
   ```bash
   # Create the database
   ./scripts/setup_database.sh
   ```
   Note: Make sure PostgreSQL is running and the credentials in the script match your setup.
   The default credentials are:
   - Username: postgres
   - Password: postgres
   - Database: customerdb

   If you need to change these, update both:
   - `scripts/setup_database.sh`
   - `src/main/resources/application.properties`

3. Local Development
   ```bash
   # Clone the repository
   git clone [repository-url]
   
   # Navigate to project directory
   cd customer-service
   
   # Build the project
   mvn clean install
   
   # Run the application
   mvn spring-boot:run
   ```

4. API Documentation
   - Swagger UI available at: `http://localhost:8080/swagger-ui.html`

## Deployment

### Local
- Configure PostgreSQL connection in `application.properties`
- Run using Maven or your IDE

### AWS
- Package the application
- Deploy using AWS Elastic Beanstalk or EC2
- Configure environment variables for database connection
