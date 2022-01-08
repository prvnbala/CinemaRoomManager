package cinema.services;

import cinema.models.Cinema;
import cinema.models.Seat;

import java.util.List;

public class CinemaService {

    Cinema cinema;

    private int totalTickets = 0;
    private int purchasedTickets = 0;
    private double purchasePercent = 0.00;
    private int currentIncome = 0;
    private int totalIncome = 0;

    public CinemaService(Cinema cinema) {
        this.cinema = cinema;

        for (List<Seat> row : cinema.getSeatList()) {
            for (Seat x : row) {
                totalTickets++;
                totalIncome += x.getPrice();
            }
        }
    }



    public void viewSeats() {
        List<List<Seat>> seatList= cinema.getSeatList();
        int row = seatList.size();
        int col = seatList.get(0).size();

        System.out.println(cinema.getName()+":");
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(seatList.get(i - 1).get(j - 1).getStatus() + " ");
                }
            }
            System.out.println();
        }
    }

    public void updateStatistics() {

        purchasedTickets = 0;
        currentIncome = 0;
        for (List<Seat> row : cinema.getSeatList()) {
            for (Seat x : row) {
                if (x.getStatus() == 'B') {
                    purchasedTickets++;
                    currentIncome += x.getPrice();
                }
            }
        }

        purchasePercent = 1.0 * purchasedTickets / totalTickets * 100;
    }

    public void getTotalIncome() {
        System.out.println("Total Income: $" + totalIncome);
    }

    public void getCurrentIncome() {
        System.out.println("Current Income: $" + currentIncome);
    }

    public void getPurchasedTicketCount() {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
    }

    public void getOccupancyPercent() {
        System.out.printf("Percentage: %.2f", purchasePercent);
        System.out.println("%");
    }

}
