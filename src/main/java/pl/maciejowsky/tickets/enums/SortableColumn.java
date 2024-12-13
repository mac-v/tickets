package pl.maciejowsky.tickets.enums;

public enum SortableColumn {
    FIRST_NAME("employee.name", "First Name"),
    LAST_NAME("employee.surname", "Last Name"),
    PHONE_NUMBER("employee.phoneNumber", "Phone no."),
    COMPANY("employee.company.name", "Company"),
    SIGNATURE("ticketSignature", "Signature"),
    VIOLATION_DATE("violanceDate", "Violation Date"),
    REASON("reason", "Reason"),
    TICKET_AMOUNT("ticketAmount", "Ticket Amount"),
    TICKET_CURRENCY("ticketCurrency", "Ticket Currency"),
    FEE_AMOUNT("feeAmount", "Fee Amount"),
    FEE_CURRENCY("feeCurrency", "Fee Currency"),
    PAYMENT_DATE("paymentDate", "Payment Date"),
    STATUS("status", "Status");

    private final String column;
    private final String label;

    SortableColumn(String column, String label) {
        this.column = column;
        this.label = label;
    }

    public String getColumn() {
        return column;
    }

    public String getLabel() {
        return label;
    }
}
