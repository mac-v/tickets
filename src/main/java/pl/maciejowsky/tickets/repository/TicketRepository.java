package pl.maciejowsky.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.tickets.domain.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t " +
            "LEFT JOIN t.employee e " +
            "WHERE e.name LIKE %:searchTerm% " +
            "OR e.surname LIKE %:searchTerm% " +
            "OR t.ticketSignature LIKE %:searchTerm% " +
            "OR e.phoneNumber LIKE %:searchTerm%")
    List<Ticket> searchTickets(@Param("searchTerm") String searchTerm);

}