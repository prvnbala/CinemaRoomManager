package cinema.services;

import cinema.exceptions.SeatAlreadyBookedException;
import cinema.models.BookingStatus;
import cinema.models.Cinema;
import cinema.models.Seat;

import java.util.List;

public class BookingService {

    private List<Seat> seatList;

    public static BookingStatus bookTicket(Seat seat) throws SeatAlreadyBookedException {
        if (seat.getStatus() == 'S') {
            System.out.println("Ticket price: $" + seat.getPrice());
            seat.setStatus('B');
            return BookingStatus.SUCCESS;
        } else {
            throw new SeatAlreadyBookedException();
        }
    }
}
