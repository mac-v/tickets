package pl.maciejowsky.tickets.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

    @GetMapping("tickets")
    public String getTickets() {
        return "index";
    }

}
