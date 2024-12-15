package pl.maciejowsky.tickets.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.maciejowsky.tickets.domain.Employee;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.dto.TicketDTO;
import pl.maciejowsky.tickets.dto.TicketFilterDTO;
import pl.maciejowsky.tickets.repository.EmployeeRepository;
import pl.maciejowsky.tickets.repository.TicketRepository;
import pl.maciejowsky.tickets.specification.TicketSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public List<Ticket> searchTickets(TicketFilterDTO filterDTO) {
        Specification<Ticket> specification = TicketSpecification.buildSpecification(filterDTO);
        return ticketRepository.findAll(specification);
    }


    public void addTicket(TicketDTO ticketDTO) {
        validateTicketSignature(ticketDTO.getTicketSignature());

        Employee employee = resolveEmployee(ticketDTO);

        Ticket ticket = mapTicketDTOToEntity(ticketDTO, employee);

        ticketRepository.save(ticket);
    }


    private void validateTicketSignature(String ticketSignature) {
        if (ticketRepository.existsTicketsByticketSignature(ticketSignature)) {
            throw new IllegalArgumentException("Signature already exists!");
        }
    }


    private Employee resolveEmployee(TicketDTO ticketDTO) {
        Employee employee = null;

        if (pl.maciejowsky.tickets.enums.Employee.CLERK.name().equals(ticketDTO.getEmployeeType())) {
            employee = createNewEmployee(
                    ticketDTO.getClerkEmployeeFirstName(),
                    ticketDTO.getClerkEmployeeLastName(),
                    pl.maciejowsky.tickets.enums.Employee.CLERK.name()
            );
        } else if (pl.maciejowsky.tickets.enums.Employee.LABOURER.name().equals(ticketDTO.getEmployeeType())) {
            employee = fetchExistingEmployee(ticketDTO.getLabourerEmployeeId());
        }

        return employee;
    }

    private Employee createNewEmployee(String firstName, String lastName, String employeeType) {
        Employee employee = new Employee();
        employee.setName(firstName);
        employee.setSurname(lastName);
        employee.setEmployeeType(employeeType);
        return employeeRepository.saveAndFlush(employee);
    }


    private Employee fetchExistingEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }


    private Ticket mapTicketDTOToEntity(TicketDTO ticketDTO, Employee employee) {
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);

        if (employee != null) {
            ticket.setEmployee(employee);
        }

        if ("OTHER".equals(ticketDTO.getReason())) {
            ticket.setReason(ticketDTO.getCustomReason());
        }

        return ticket;
    }
}