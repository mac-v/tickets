package pl.maciejowsky.tickets.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.dto.TicketFilterDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TicketSpecification {

    public static Specification<Ticket> buildSpecification(TicketFilterDTO filterDTO) {
        return (Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            addSearchQueryPredicate(filterDTO.getSearchQuery(), root, criteriaBuilder, predicates);
            addInPredicate(filterDTO.getReasons(), "reason", root, criteriaBuilder, predicates);
            addInPredicate(filterDTO.getCurrencies(), "ticketCurrency", root, criteriaBuilder, predicates);
            addInPredicate(filterDTO.getStatuses(), "status", root, criteriaBuilder, predicates);
            addDatePredicate(filterDTO.getViolationDateFrom(), "violanceDate", root, criteriaBuilder, predicates, true);
            addDatePredicate(filterDTO.getViolationDateTo(), "violanceDate", root, criteriaBuilder, predicates, false);
            addDatePredicate(filterDTO.getDueDateFrom(), "paymentDate", root, criteriaBuilder, predicates, true);
            addDatePredicate(filterDTO.getDueDateTo(), "paymentDate", root, criteriaBuilder, predicates, false);
            addInPredicate(filterDTO.getCompanyNames(), "employee.company.name", root, criteriaBuilder, predicates);
            addOrderBy(filterDTO.getOrderBy(), filterDTO.getOrderDirection(), query, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void addSearchQueryPredicate(String searchQuery, Root<Ticket> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (searchQuery != null && !searchQuery.isEmpty()) {
            String search = "%" + searchQuery.toLowerCase() + "%";
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("employee").get("name")), search),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("employee").get("surname")), search),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("ticketSignature")), search),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("employee").get("phoneNumber")), search)
            ));
        }
    }

    private static void addOrderBy(String orderBy, String orderDirection, CriteriaQuery<?> query, Root<Ticket> root, CriteriaBuilder criteriaBuilder) {
        if (orderBy != null && !orderBy.isEmpty()) {
            if ("desc".equalsIgnoreCase(orderDirection)) {
                query.orderBy(criteriaBuilder.desc(getFieldPath(orderBy, root)));
            } else {
                query.orderBy(criteriaBuilder.asc(getFieldPath(orderBy, root)));
            }
        }
    }

    private static Path<?> getFieldPath(String fieldPath, Root<?> root) {
        String[] fieldComponents = fieldPath.split("\\.");
        Path<?> path = root;

        for (String field : fieldComponents) {
            path = path.get(field); // Dynamically navigate
        }
        return path;
    }


    private static void addInPredicate(List<String> values, String field, Root<Ticket> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (values != null && !values.isEmpty()) {
            if ("employee.company.name".equals(field)) {
                predicates.add(root.get("employee").get("company").get("name").in(values));
            } else if ("ticketCurrency".equals(field)) {
                Predicate ticketCurrencyPredicate = root.get("ticketCurrency").in(values);
                Predicate feeCurrencyPredicate = root.get("feeCurrency").in(values);
                predicates.add(criteriaBuilder.or(ticketCurrencyPredicate, feeCurrencyPredicate));
            } else {
                predicates.add(root.get(field).in(values));
            }
        }
    }


    private static void addDatePredicate(String date, String field, Root<Ticket> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, boolean isGreaterThanOrEqual) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            if (isGreaterThanOrEqual) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field), localDate));
            } else {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(field), localDate));
            }
        }
    }
}
