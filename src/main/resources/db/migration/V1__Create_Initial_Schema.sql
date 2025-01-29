-- Create Customer table
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Create Location table
CREATE TABLE location (
    id BIGSERIAL PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    customer_id BIGINT NOT NULL,
    CONSTRAINT fk_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer (id)
        ON DELETE CASCADE
);

-- Create Temperature Criteria table
CREATE TABLE temperature_criteria (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    threshold DOUBLE PRECISION NOT NULL,
    location_id BIGINT NOT NULL,
    CONSTRAINT fk_location
        FOREIGN KEY (location_id)
        REFERENCES location (id)
        ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX idx_customer_email ON customer(email);
CREATE INDEX idx_location_customer ON location(customer_id);
CREATE INDEX idx_temperature_criteria_location ON temperature_criteria(location_id);
