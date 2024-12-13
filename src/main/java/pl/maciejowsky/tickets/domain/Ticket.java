package pl.maciejowsky.tickets.domain;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Ticket_Employee"))
    private Employee employee;

    @Column(name = "ticket_signature", length = 50, nullable = false)
    private String ticketSignature;

    @Column(name = "violance_date", nullable = false)
    private LocalDate violanceDate;

    @Column(length = 255, nullable = false)
    private String reason;

    @Column(name = "ticket_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal ticketAmount;

    @Column(name = "ticket_currency", length = 10, nullable = false)
    private String ticketCurrency;

    @Column(name = "fee_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal feeAmount;

    @Column(name = "fee_currency", length = 10, nullable = false)
    private String feeCurrency;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(length = 50, nullable = false)
    private String status;

}