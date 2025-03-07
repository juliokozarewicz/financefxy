CREATE TABLE IF NOT EXISTS category (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    category_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    transaction_name VARCHAR(100) NOT NULL,
    transaction_type VARCHAR(100) DEFAULT 'expense' NOT NULL,
    payment_description VARCHAR(255) NOT NULL,
    payment_amount DECIMAL(18, 2) NOT NULL,
    due_date DATE NOT NULL,
    payee VARCHAR(250),
    document_number VARCHAR(250),
    category VARCHAR(100) NOT NULL,
    bank_account VARCHAR(100) NOT NULL,
    card VARCHAR(100),
    notes VARCHAR(500),
    status VARCHAR(50) DEFAULT 'pending' NOT NULL
);
