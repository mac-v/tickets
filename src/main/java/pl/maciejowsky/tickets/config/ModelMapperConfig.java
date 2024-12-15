package pl.maciejowsky.tickets.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.maciejowsky.tickets.domain.Ticket;
import pl.maciejowsky.tickets.dto.TicketDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(TicketDTO.class, Ticket.class).addMappings(mapper -> {
            mapper.map(TicketDTO::getClerkEmployeeFirstName,
                    (dest, value) -> dest.getEmployee().setName((String) value));

            mapper.map(TicketDTO::getClerkEmployeeLastName,
                    (dest, value) -> dest.getEmployee().setSurname((String) value));
        });
        return modelMapper;
    }
}