package pl.maciejowsky.tickets.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TicketDTO {

    private String employeeType;

    private Long labourerEmployeeId;

    private String clerkEmployeeFirstName;

    private String clerkEmployeeLastName;

    private String ticketSignature;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate violanceDate;

    private String reason;
    private String customReason;

    private BigDecimal ticketAmount;

    private BigDecimal feeAmount;

    private String ticketCurrency;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    private MultipartFile pdfFile;


}