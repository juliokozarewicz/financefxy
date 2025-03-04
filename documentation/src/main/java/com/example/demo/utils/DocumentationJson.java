package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocumentationJson {

    @Value("${APPLICATION_TITLE}")
    private String applicationTitle;

    public String documentationText() {

        String docs = """
        
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
                    }
                    # ==========================================================
                    # ==========================================================

                }
            }
        
        """.formatted(applicationTitle);

        return docs;

    }


}
