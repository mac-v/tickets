package pl.maciejowsky.tickets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.maciejowsky.tickets.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<String> getCompanyNames() {
        return companyRepository.findAll()
                .stream()
                .map(company -> company.getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
