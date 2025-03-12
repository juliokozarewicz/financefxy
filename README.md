# Financefxy - Personal Finance System

**Financefxy** is a personal finance system developed in Java, with a microservices architecture, using state-of-the-art technologies to ensure high performance, scalability, and maintainability. This system is designed to provide a simple and efficient way to manage personal finances, with features to track financial transactions, categorize expenses and income, and other essential functionalities for those who want to maintain full control over their finances.

## Technologies Used

- **Java**: The primary programming language used for system development.
- **Spring Framework**: Used to build the base of the microservices, with Spring Boot simplifying the API setup and configuration.
- **Hibernate**: For object-relational mapping (ORM) and seamless interaction with the database.
- **Flyway**: Database version control tool, making schema migrations easier.
- **Lombok**: Reduces boilerplate code by automatically generating getters, setters, and other utility methods.
- **NGINX**: Acts as an API Gateway, managing incoming traffic to the microservices.
- **Microservices**: Architecture based on microservices, allowing scalability and independence between parts of the system.
- **PostgreSQL**: Relational database used for data persistence.
- **Logs**: Logging implementation to track and monitor the system’s behavior.
- **Error Handler**: Centralized error handling to ensure appropriate responses and consistency throughout the system.
- **Swagger and Redocly**: API documentation tools, making it easy for developers and users to interact with the API.
- **Input Validation**: Ensures input data is correct and meets requirements before being processed.
- **Docker**: Containerization of the system to streamline deployment and ensure that the development environment matches production.
- **Internationalization**: Support for multiple languages, with server-side translation.

## Features

- **Financial Transaction Management**: Allows creating both debit and credit financial transactions.
- **Expense and Income Categorization**: Transactions can be categorized for easier financial tracking.
- **Reports Visualization**: Detailed reports of finances, including income, expenses, balances, etc.
- **Integration with Beneficiary and Payer API**: Allows recording information about the entities involved in transactions.
- **Authentication and Security**: Uses JWT (JSON Web Tokens) for user authentication and transaction security.
- **Multi-language Support**: The system can be easily translated into different languages, ensuring accessibility for a global user base.
