CREATE TABLE Ticket
(
    id               BIGINT IDENTITY(1,1) PRIMARY KEY,
    employee_id      BIGINT         NOT NULL,
    ticket_signature VARCHAR(50)    NOT NULL,
    violance_date    DATE           NOT NULL,
    reason           VARCHAR(255)   NOT NULL,
    ticket_amount           DECIMAL(10, 2) NOT NULL,
    ticket_currency  VARCHAR(10)    NOT NULL,
    fee_amount              DECIMAL(10, 2) NOT NULL,
    fee_currency     VARCHAR(10)    NOT NULL,
    payment_date     DATE           NOT NULL,
    status           VARCHAR(50)    NOT NULL,
    CONSTRAINT FK_Ticket_Employee FOREIGN KEY (employee_id) REFERENCES Employee (id)
);