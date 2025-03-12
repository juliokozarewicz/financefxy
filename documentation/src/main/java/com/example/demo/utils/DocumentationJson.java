package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocumentationJson {

    @Value("${APPLICATION_TITLE}")
    private String applicationTitle;

    public String documentationText() {

        String docs = new StringBuilder()

            .append(
                """
                {
                "openapi":"3.0.0",
                "info":{
                    "title":"%s",
                    "version":"1.0",
                    "description":"The personal finance API is designed to offer a simple and efficient way to manage your finances, providing full control over your financial transactions. With it, you can create both debits and credits, recording all the details of each transaction, such as categories, amounts, and due dates. The tool allows you to gain a clear view of your financial flows, organizing your spending and income in an intuitive and straightforward manner. By closely monitoring your entries, you can avoid surprises and ensure all payments are made on time. \\n\\nAdditionally, the API provides the ability to register information about beneficiaries and payers, creating an organized structure to track who you pay and who owes you. This approach offers complete freedom and flexibility for those looking to control their finances in a practical and detailed way, ensuring greater security and peace of mind in reaching their financial goals."
                },
                "components":{
                    "securitySchemes":{
                        "BearerAuth":{
                            "type":"http",
                            "scheme":"bearer",
                            "bearerFormat":"JWT"
                        }
                    }
                },
                """
            )

            .append(
                """
                "paths":{

                # HELLOWORLD
                # ==========================================================
                "/helloworld/helloworld":{
                    "get":{
                        "summary":"Get hello world message",
                        "description":"Retrieves a hello world message. You can optionally provide a custom message via query parameter.",
                        "tags":[
                            "HELLO WORLD"
                        ],
                        "parameters":[
                            {
                                "name":"message",
                                "in":"query",
                                "required":false,
                                "description":"Custom message to be returned. Defaults to 'Hello World!' if not provided.",
                                "schema":{
                                    "type":"string",
                                    "example":"Hello from the API!"
                                }
                            }
                        ],
                        "responses":{
                            "200":{
                                "description":"Successful response with hello world message.",
                                "content":{
                                    "application/json":{
                                        "schema":{
                                            "type":"object",
                                            "properties":{
                                                "statusCode":{
                                                    "type":"integer",
                                                    "example":200
                                                },
                                                "statusMessage":{
                                                    "type":"string",
                                                    "example":"success"
                                                },
                                                "message":{
                                                    "type":"string",
                                                    "example":"Data received successfully. (Hello World!)"
                                                },
                                                "links":{
                                                    "type":"object",
                                                    "properties":{
                                                        "self":{
                                                            "type":"string",
                                                            "example":"/helloworld/helloworld"
                                                        },
                                                        "next":{
                                                            "type":"string",
                                                            "example":"/documentation/swagger"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                """
            )

            .append(
                """
                # CATEGORY
                # ==========================================================
                "/finance/category/create": {
                    "post": {
                        "summary": "Create a new category",
                        "description": "Creates a new category in the system. If the category already exists, a conflict error is returned.",
                        "tags": [
                            "CATEGORY"
                        ],
                        "requestBody": {
                            "required": true,
                            "content": {
                                "application/json": {
                                    "schema": {
                                        "type": "object",
                                        "properties": {
                                            "categoryName": {
                                                "type": "string",
                                                "example": "New Category",
                                                "description": "The name of the category to be created. Should be non-empty and not exceed 100 characters."
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        "responses": {
                            "201": {
                                "description": "Category created successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 201
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Category created successfully."
                                                },
                                                "meta": {
                                                    "type": "object",
                                                    "properties": {
                                                        "idCreated": {
                                                            "type": "string",
                                                            "example": "9b750213-c560-4766-a1e7-14303f84a14f"
                                                        }
                                                    }
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/category/create"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/category/list"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "409": {
                                "description": "Conflict: Category already exists.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 409
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "This category already exists."
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "400": {
                                "description": "Bad Request: Category name cannot be empty.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 400
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "field": {
                                                    "type": "string",
                                                    "example": "categoryName"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Cannot be empty."
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                "/finance/category/update/{id}": {
                    "put": {
                        "summary": "Update an existing category",
                        "description": "Updates the category with the specified ID. If the category does not exist, a not found error is returned. If the category name already exists, a conflict error is returned.",
                        "tags": [
                            "CATEGORY"
                        ],
                        "parameters": [
                            {
                                "name": "id",
                                "in": "path",
                                "required": true,
                                "schema": {
                                    "type": "string",
                                    "example": "9b750213-c560-4766-a1e7-14303f84a14f",
                                    "description": "The UUID of the category to be updated."
                                }
                            }
                        ],
                        "requestBody": {
                            "required": true,
                            "content": {
                                "application/json": {
                                    "schema": {
                                        "type": "object",
                                        "properties": {
                                            "categoryName": {
                                                "type": "string",
                                                "example": "Updated Category",
                                                "description": "The new name of the category. Should be non-empty and not exceed 100 characters."
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        "responses": {
                            "200": {
                                "description": "Category updated successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 200
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Category updated successfully."
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/category/update/9b750213-c560-4766-a1e7-14303f84a14f"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/category/list"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "409": {
                                "description": "Conflict: Category already exists.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 409
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "This category already exists."
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "404": {
                                "description": "Not Found: Category not found.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 404
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Category not found."
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "400": {
                                "description": "Bad Request: Category name cannot be empty.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 400
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "field": {
                                                    "type": "string",
                                                    "example": "categoryName"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Cannot be empty."
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                "/finance/category/delete/{id}": {
                    "delete": {
                        "summary": "Delete an existing category",
                        "description": "Deletes the category with the specified ID. If the category does not exist, a not found error is returned.",
                        "tags": [
                            "CATEGORY"
                        ],
                        "parameters": [
                            {
                                "name": "id",
                                "in": "path",
                                "required": true,
                                "schema": {
                                    "type": "string",
                                    "example": "9b750213-c560-4766-a1e7-14303f84a14f",
                                    "description": "The UUID of the category to be deleted."
                                }
                            }
                        ],
                        "responses": {
                            "200": {
                                "description": "Category deleted successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 200
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Category deleted successfully."
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/category/delete/9b750213-c560-4766-a1e7-14303f84a14f"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/category/list"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "404": {
                                "description": "Not Found: Category not found.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 404
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Category not found."
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                "/finance/category/list-all": {
                    "get": {
                        "summary": "Get a list of all categories",
                        "description": "Fetches a list of all categories from the database. The response includes the total number of items, and links for navigation.",
                        "tags": [
                            "CATEGORY"
                        ],
                        "responses": {
                            "200": {
                                "description": "Categories retrieved successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 200
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Data received successfully."
                                                },
                                                "data": {
                                                    "type": "array",
                                                    "items": {
                                                        "type": "object",
                                                        "properties": {
                                                            "id": {
                                                                "type": "string",
                                                                "example": "850937c9-90e4-4ead-89db-a66a2dee6f2e"
                                                            },
                                                            "categoryName": {
                                                                "type": "string",
                                                                "example": "credit card"
                                                            }
                                                        }
                                                    },
                                                    "description": "List of all categories."
                                                },
                                                "meta": {
                                                    "type": "object",
                                                    "properties": {
                                                        "totalItems": {
                                                            "type": "integer",
                                                            "example": 1
                                                        }
                                                    },
                                                    "description": "Meta information about the response, including the total number of categories."
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/category/list-all"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/category/update"
                                                        }
                                                    },
                                                    "description": "Links for navigation."
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                """
            )

            .append(
                """
                # TRANSACTION
                # ==========================================================
                "/finance/transaction/create": {
                    "post": {
                        "summary": "Create a new transaction",
                        "description": "Creates a new transaction in the system. The transaction details are validated before saving in the database. If a transaction with the same name and due date already exists, a conflict error will be returned.",
                        "tags": [
                            "TRANSACTION"
                        ],
                        "requestBody": {
                            "required": true,
                            "content": {
                                "application/json": {
                                    "schema": {
                                        "type": "object",
                                        "properties": {
                                            "transactionName": {
                                                "type": "string",
                                                "example": "Service Payment"
                                            },
                                            "transactionType": {
                                                "type": "string",
                                                "example": "Debit"
                                            },
                                            "paymentDescription": {
                                                "type": "string",
                                                "example": "Payment for the service rendered"
                                            },
                                            "paymentAmount": {
                                                "type": "number",
                                                "format": "float",
                                                "example": 1000.00
                                            },
                                            "dueDate": {
                                                "type": "string",
                                                "format": "date-time",
                                                "example": "2025-03-10T14:30:00"
                                            },
                                            "payee": {
                                                "type": "string",
                                                "example": "John Doe"
                                            },
                                            "documentNumber": {
                                                "type": "string",
                                                "example": "1234567890"
                                            },
                                            "category": {
                                                "type": "string",
                                                "example": "Services"
                                            },
                                            "bankAccount": {
                                                "type": "string",
                                                "example": "1234-5"
                                            },
                                            "card": {
                                                "type": "string",
                                                "example": "9876-5432-1098-7654"
                                            },
                                            "notes": {
                                                "type": "string",
                                                "example": "Payment made with discount"
                                            },
                                            "status": {
                                                "type": "string",
                                                "example": "Pending"
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        "responses": {
                            "201": {
                                "description": "Transaction created successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 201
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Transaction created successfully."
                                                },
                                                "meta": {
                                                    "type": "object",
                                                    "properties": {
                                                        "idCreated": {
                                                            "type": "string",
                                                            "example": "860a541e-de21-4b4e-b9a8-e49575c82ab0"
                                                        }
                                                    },
                                                    "description": "Meta information about the response, including the transaction ID created."
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/create"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/list-all"
                                                        }
                                                    },
                                                    "description": "Links for navigation."
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "409": {
                                "description": "Transaction already exists.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 409
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "This transaction already exists."
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "400": {
                                "description": "Bad request (validation error).",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 400
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "field": {
                                                    "type": "string",
                                                    "example": "transactionName"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Cannot be empty."
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                "/finance/transaction/update/{id}": {
                    "put": {
                        "summary": "Update an existing transaction",
                        "description": "Updates the details of an existing transaction. The transaction is identified by its UUID. Only the fields provided in the request body will be updated. If a field is not provided, it will retain its current value. If the transaction with the given ID is not found, a 404 error will be returned.",
                        "tags": [
                            "TRANSACTION"
                        ],
                        "parameters": [
                            {
                                "name": "id",
                                "in": "path",
                                "required": true,
                                "description": "UUID of the transaction to update",
                                "schema": {
                                    "type": "string",
                                    "example": "f9422414-1c9f-4016-bf13-d679ffe4c0db"
                                }
                            }
                        ],
                        "requestBody": {
                            "required": true,
                            "content": {
                                "application/json": {
                                    "schema": {
                                        "type": "object",
                                        "properties": {
                                            "transactionName": {
                                                "type": "string",
                                                "example": "Service Payment"
                                            },
                                            "transactionType": {
                                                "type": "string",
                                                "example": "Debit"
                                            },
                                            "paymentDescription": {
                                                "type": "string",
                                                "example": "Payment for the service rendered"
                                            },
                                            "paymentAmount": {
                                                "type": "number",
                                                "format": "float",
                                                "example": 1000.00
                                            },
                                            "dueDate": {
                                                "type": "string",
                                                "format": "date-time",
                                                "example": "2025-03-10T14:30:00"
                                            },
                                            "payee": {
                                                "type": "string",
                                                "example": "John Doe"
                                            },
                                            "documentNumber": {
                                                "type": "string",
                                                "example": "1234567890"
                                            },
                                            "category": {
                                                "type": "string",
                                                "example": "Services"
                                            },
                                            "bankAccount": {
                                                "type": "string",
                                                "example": "1234-5"
                                            },
                                            "card": {
                                                "type": "string",
                                                "example": "9876-5432-1098-7654"
                                            },
                                            "notes": {
                                                "type": "string",
                                                "example": "Payment made with discount"
                                            },
                                            "status": {
                                                "type": "string",
                                                "example": "Pending"
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        "responses": {
                            "200": {
                                "description": "Transaction updated successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 200
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Transaction updated successfully."
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/update/f9422414-1c9f-4016-bf13-d679ffe4c0db"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/list-all"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "404": {
                                "description": "Transaction not found.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 404
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Transaction not found."
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "400": {
                                "description": "Bad request (validation error).",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 400
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "field": {
                                                    "type": "string",
                                                    "example": "transactionName"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Cannot be empty."
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                "/finance/transaction/delete/{id}": {
                    "delete": {
                        "summary": "Delete an existing transaction",
                        "description": "Deletes an existing transaction identified by its UUID. If the transaction with the given ID is not found, a 404 error will be returned. Upon successful deletion, a success message is returned with appropriate response links.",
                        "tags": [
                            "TRANSACTION"
                        ],
                        "parameters": [
                            {
                                "name": "id",
                                "in": "path",
                                "required": true,
                                "description": "UUID of the transaction to delete",
                                "schema": {
                                    "type": "string",
                                    "example": "f9422414-1c9f-4016-bf13-d679ffe4c0db"
                                }
                            }
                        ],
                        "responses": {
                            "200": {
                                "description": "Transaction deleted successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 200
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Transaction deleted successfully."
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/delete/15eefecf-6092-4f5c-8597-7907fcb8ea96"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/list-all"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "404": {
                                "description": "Transaction not found.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 404
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Transaction not found."
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "400": {
                                "description": "Bad request (validation error).",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 400
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "error"
                                                },
                                                "field": {
                                                    "type": "string",
                                                    "example": "uuid"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "The UUID is not valid."
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                "/finance/transaction/list-all": {
                    "get": {
                        "summary": "Retrieve a list of all transactions",
                        "description": "Fetches a list of all transactions based on the provided filter criteria. If no filters are provided, all transactions are returned. The response includes pagination details and links to navigate through the results.",
                        "tags": [
                            "TRANSACTION"
                        ],
                        "parameters": [
                            {
                                "name": "transactionName",
                                "in": "query",
                                "required": false,
                                "description": "Transaction name filter",
                                "schema": {
                                    "type": "string",
                                    "example": "Service Payment"
                                }
                            },
                            {
                                "name": "transactionType",
                                "in": "query",
                                "required": false,
                                "description": "Transaction type filter (e.g., Debit, Credit)",
                                "schema": {
                                    "type": "string",
                                    "example": "Debit"
                                }
                            },
                            {
                                "name": "paymentDescription",
                                "in": "query",
                                "required": false,
                                "description": "Payment description filter",
                                "schema": {
                                    "type": "string",
                                    "example": "Payment for the service rendered"
                                }
                            },
                            {
                                "name": "paymentAmount",
                                "in": "query",
                                "required": false,
                                "description": "Payment amount filter",
                                "schema": {
                                    "type": "number",
                                    "example": 1000.00
                                }
                            },
                            {
                                "name": "initDate",
                                "in": "query",
                                "required": false,
                                "description": "Initial date filter for transactions",
                                "schema": {
                                    "type": "string",
                                    "format": "date",
                                    "example": "2025-03-01"
                                }
                            },
                            {
                                "name": "endDate",
                                "in": "query",
                                "required": false,
                                "description": "End date filter for transactions",
                                "schema": {
                                    "type": "string",
                                    "format": "date",
                                    "example": "2025-03-31"
                                }
                            },
                            {
                                "name": "payee",
                                "in": "query",
                                "required": false,
                                "description": "Payee filter",
                                "schema": {
                                    "type": "string",
                                    "example": "John Doe"
                                }
                            },
                            {
                                "name": "documentNumber",
                                "in": "query",
                                "required": false,
                                "description": "Document number filter",
                                "schema": {
                                    "type": "string",
                                    "example": "1234567890"
                                }
                            },
                            {
                                "name": "category",
                                "in": "query",
                                "required": false,
                                "description": "Category filter",
                                "schema": {
                                    "type": "string",
                                    "example": "Services"
                                }
                            },
                            {
                                "name": "bankAccount",
                                "in": "query",
                                "required": false,
                                "description": "Bank account filter",
                                "schema": {
                                    "type": "string",
                                    "example": "1234-5"
                                }
                            },
                            {
                                "name": "card",
                                "in": "query",
                                "required": false,
                                "description": "Card filter",
                                "schema": {
                                    "type": "string",
                                    "example": "9876-5432-1098-7654"
                                }
                            },
                            {
                                "name": "notes",
                                "in": "query",
                                "required": false,
                                "description": "Notes filter",
                                "schema": {
                                    "type": "string",
                                    "example": "Payment made with discount"
                                }
                            },
                            {
                                "name": "status",
                                "in": "query",
                                "required": false,
                                "description": "Transaction status filter",
                                "schema": {
                                    "type": "string",
                                    "example": "Pending"
                                }
                            }
                        ],
                        "responses": {
                            "200": {
                                "description": "Data received successfully.",
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "statusCode": {
                                                    "type": "integer",
                                                    "example": 200
                                                },
                                                "statusMessage": {
                                                    "type": "string",
                                                    "example": "success"
                                                },
                                                "message": {
                                                    "type": "string",
                                                    "example": "Data received successfully."
                                                },
                                                "data": {
                                                    "type": "array",
                                                    "items": {
                                                        "type": "object",
                                                        "properties": {
                                                            "id": {
                                                                "type": "string",
                                                                "example": "05af5309-fdbc-4665-bef0-5f1d4886e1ee"
                                                            },
                                                            "transactionName": {
                                                                "type": "string",
                                                                "example": "Service Payment 2"
                                                            },
                                                            "transactionType": {
                                                                "type": "string",
                                                                "example": "Debit"
                                                            },
                                                            "paymentDescription": {
                                                                "type": "string",
                                                                "example": "Payment for the service rendered"
                                                            },
                                                            "paymentAmount": {
                                                                "type": "number",
                                                                "example": 1000.00
                                                            },
                                                            "dueDate": {
                                                                "type": "string",
                                                                "format": "date",
                                                                "example": "2025-03-01"
                                                            },
                                                            "payee": {
                                                                "type": "string",
                                                                "example": "John Doe"
                                                            },
                                                            "documentNumber": {
                                                                "type": "string",
                                                                "example": "1234567890"
                                                            },
                                                            "category": {
                                                                "type": "string",
                                                                "example": "Services"
                                                            },
                                                            "bankAccount": {
                                                                "type": "string",
                                                                "example": "1234-5"
                                                            },
                                                            "card": {
                                                                "type": "string",
                                                                "example": "9876-5432-1098-7654"
                                                            },
                                                            "notes": {
                                                                "type": "string",
                                                                "example": "Payment made with discount"
                                                            },
                                                            "status": {
                                                                "type": "string",
                                                                "example": "Pending"
                                                            }
                                                        }
                                                    }
                                                },
                                                "meta": {
                                                    "type": "object",
                                                    "properties": {
                                                        "totalItems": {
                                                            "type": "integer",
                                                            "example": 1
                                                        }
                                                    }
                                                },
                                                "links": {
                                                    "type": "object",
                                                    "properties": {
                                                        "self": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/list-all"
                                                        },
                                                        "next": {
                                                            "type": "string",
                                                            "example": "/finance/transaction/update/{id}"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                # ==========================================================
                """
            )

            .append("}}")
        .toString().formatted(applicationTitle);

        return docs;

    }

}