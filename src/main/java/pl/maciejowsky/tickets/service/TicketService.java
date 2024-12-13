package pl.maciejowsky.tickets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.repository.TicketRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;


    public List<Ticket> searchTicket(String searchQuery) {
        return ticketRepository.searchTickets(searchQuery);
    }
}
