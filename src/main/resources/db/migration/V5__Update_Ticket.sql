ALTER TABLE Ticket
    ADD CONSTRAINT DF_fee_amount DEFAULT 100.00 FOR fee_amount;

ALTER TABLE Ticket
    ADD CONSTRAINT DF_fee_currency DEFAULT 'PLN' FOR fee_currency;
