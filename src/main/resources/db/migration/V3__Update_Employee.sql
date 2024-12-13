ALTER TABLE Employee ADD employee_type VARCHAR(50) NOT NULL;
ALTER TABLE Employee ADD company_id BIGINT NULL;
ALTER TABLE Employee ADD status VARCHAR(20) NULL;

-- Adding a foreign key constraint for company_id
ALTER TABLE Employee
    ADD CONSTRAINT FK_Employee_Company FOREIGN KEY (company_id) REFERENCES Company(id);