package pl.maciejowsky.tickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import pl.maciejowsky.tickets.enums.Currency;
import pl.maciejowsky.tickets.enums.Reason;
import pl.maciejowsky.tickets.enums.Status;
import pl.maciejowsky.tickets.repository.CompanyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ThymeleafConfig {


    private static final List<String> REASON_LIST = Arrays.stream(Reason.values())
            .map(Reason::getDescription)
            .toList();
    private static final List<Currency> CURRENCY_LIST = List.of(Currency.values());
    private static final List<Status> STATUS_LIST = List.of(Status.values());

    private final List<String> companyNames;

    public ThymeleafConfig(CompanyRepository companyRepository) {

        this.companyNames = companyRepository.findAll()
                .stream()
                .map(company -> company.getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);

        viewResolver.setStaticVariables(Map.of(
                "ticketReasons", REASON_LIST,
                "currencyOptions", CURRENCY_LIST,
                "paymentStatuses", STATUS_LIST,
                "companyNames", companyNames
        ));
        System.out.println(companyNames);
        return viewResolver;
    }
}