package pl.maciejowsky.tickets.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.dto.TicketDTO;
import pl.maciejowsky.tickets.dto.TicketFilterDTO;
import pl.maciejowsky.tickets.repository.EmployeeRepository;
import pl.maciejowsky.tickets.repository.TicketRepository;
import pl.maciejowsky.tickets.service.CompanyService;
import pl.maciejowsky.tickets.service.TicketService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    private final CompanyService companyService;

    private final EmployeeRepository employeeRepository;


    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/tickets")
    public String getTickets(
            Model model) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        model.addAttribute("companyNames", companyService.getCompanyNames());
        model.addAttribute("searchForm", new TicketFilterDTO());
        model.addAttribute("employees", employeeRepository.findByEmployeeType("LABOURER"));
        model.addAttribute("addForm", new TicketDTO());

        return "tickets";
    }

    @GetMapping("/tickets/search")
    public String searchTickets(@ModelAttribute("searchForm") TicketFilterDTO filterDTO, Model model) {
        List<Ticket> tickets = ticketService.searchTickets(filterDTO);
        model.addAttribute("tickets", tickets);
        model.addAttribute("searchForm", filterDTO);
        return "tickets";
    }

    @PostMapping("/tickets")
    public String addTicket(@ModelAttribute("ticket") TicketDTO ticketDTO, Model model) {
        try {
            ticketService.addTicket(ticketDTO);
            return "redirect:/tickets";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("tickets", ticketRepository.findAll());
            model.addAttribute("companyNames", companyService.getCompanyNames());
            model.addAttribute("searchForm", new TicketFilterDTO());
            model.addAttribute("employees", employeeRepository.findByEmployeeType("LABOURER"));
            model.addAttribute("addForm", new TicketDTO());
            return "tickets";
        }
    }


}