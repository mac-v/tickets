INSERT INTO Employee (name, surname, employee_type)
VALUES ('John', 'Doe', 'clerk');


INSERT INTO Ticket (employee_id, ticket_signature, violance_date, reason,
                    ticket_amount, ticket_currency, payment_date, status)
VALUES (1, 'TICKET001', '2023-01-15',
        'Speeding', 150.00, 'PLN', '2023-02-01', 'Paid'),
       (1, 'TICKET002', '2023-03-15',
        'Speeding', 330.00, 'PLN', '2023-04-01', 'Paid');