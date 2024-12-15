package pl.maciejowsky.tickets.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "ticket_signature")
    private String ticketSignature;

    @Column(name = "violance_date")
    private LocalDate violanceDate;

    @Column()
    private String reason;

    @Column(name = "ticket_amount")
    private BigDecimal ticketAmount;

    @Column(name = "ticket_currency")
    private String ticketCurrency;

    @Column(name = "fee_amount")
    private BigDecimal feeAmount;

    @Column(name = "fee_currency")
    private String feeCurrency = "PLN";

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column()
    private String status = "UNPAID";

    @Column(name = "pdf_file")
    private byte[] pdfFile;
}