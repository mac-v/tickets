package pl.maciejowsky.tickets.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.dto.TicketFilterDTO;
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
        model.addAttribute("searchForm", new TicketFilterDTO());
        return "tickets";
    }

    @GetMapping("/tickets/search")
    public String searchTickets(@ModelAttribute("searchForm") TicketFilterDTO filterDTO, Model model) {
        List<Ticket> tickets = ticketService.searchTickets(filterDTO);
        System.out.println("Currencies: " + filterDTO.getCurrencies());
        System.out.println("Statuses: " + filterDTO.getStatuses());
        model.addAttribute("tickets", tickets);
        model.addAttribute("searchForm", filterDTO);
        return "tickets";
    }


}