package pl.maciejowsky.tickets.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.repository.TicketRepository;
import pl.maciejowsky.tickets.service.TicketService;

import java.util.List;

@Controller
public class TicketController {

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    public TicketController(TicketRepository ticketRepository, TicketService ticketService) {
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public String getTickets(
            Model model) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/tickets/search")
    public String searchTickets(@RequestParam("searchQuery") String query, Model model) {
        List<Ticket> tickets = ticketService.searchTicket(query);
        System.out.println("Searching: " + query);
        System.out.println("Result: " + tickets);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }


}