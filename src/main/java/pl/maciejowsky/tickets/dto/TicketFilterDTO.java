package pl.maciejowsky.tickets.dto;

import lombok.Data;

import java.util.List;

@Data
public class TicketFilterDTO {
    private String searchQuery;
    private List<String> reasons;
    private List<String> currencies;
    private List<String> statuses;
    private String violationDateFrom;
    private String violationDateTo;
    private String dueDateFrom;
    private String dueDateTo;
    private List<String> companyNames;
    private String orderBy;
    private String orderDirection;
}