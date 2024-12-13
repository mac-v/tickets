package pl.maciejowsky.tickets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.dto.TicketFilterDTO;
import pl.maciejowsky.tickets.repository.TicketRepository;
import pl.maciejowsky.tickets.specification.TicketSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;


    public List<Ticket> searchTickets(TicketFilterDTO filterDTO) {
        Specification<Ticket> specification = TicketSpecification.buildSpecification(filterDTO);
        return ticketRepository.findAll(specification);
    }
}
