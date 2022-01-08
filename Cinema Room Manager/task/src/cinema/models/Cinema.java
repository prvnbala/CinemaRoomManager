package cinema.models;

import cinema.exceptions.SeatAlreadyBookedException;
import cinema.services.BookingService;
import cinema.services.CinemaService;
import cinema.strategies.CinemaSizeBasedPricingStrategy;
import cinema.strategies.PricingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {

    private String name = "Cinema";
    private List<List<Seat>> seatList = new ArrayList<>();
    private int seatRowCount;
    private int seatColCount;
    private PricingStrategy pricingStrategy;

    private void generateSeatLayout(int seatRowCount, int seatColCount) {
        for (int i = 0; i < seatRowCount; i++) {
            seatList.add(new ArrayList<Seat>());
            for (int j = 0; j < seatColCount; j++) {
                seatList.get(seatList.size() - 1).add(new Seat('S', pricingStrategy.getSeatPrice(i, j)));
            }
        }
    }

    public Cinema(int seatRowCount, int seatColCount, PricingStrategy pricingStrategy) {
        this.seatRowCount = seatRowCount;
        this.seatColCount = seatColCount;
        this.pricingStrategy = pricingStrategy;
        this.generateSeatLayout(seatRowCount, seatColCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Seat>> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<List<Seat>> seatList) {
        this.seatList = seatList;
    }

    public int getSeatRowCount() {
        return seatRowCount;
    }

    public void setSeatRowCount(int seatRowCount) {
        this.seatRowCount = seatRowCount;
    }

    public int getSeatColCount() {
        return seatColCount;
    }

    public void setSeatColCount(int seatColCount) {
        this.seatColCount = seatColCount;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();

        PricingStrategy pricingStrategy = new CinemaSizeBasedPricingStrategy(rows, cols);
        Cinema cinema = new Cinema(rows, cols, pricingStrategy);
        CinemaService cinemaService = new CinemaService(cinema);

        boolean active = true;
        boolean repeat = false;
        int choice = 1;
        while (active) {

            if (!repeat) {
                System.out.println("1. Show the seats");
                System.out.println("2. Buy a ticket");
                System.out.println("3. Statistics");
                System.out.println("0. Exit");

                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    cinemaService.viewSeats();
                    System.out.println();
                    break;
                case 2:

                    System.out.println("Enter a row number:");
                    int row = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int col = scanner.nextInt();

                    try {
                        BookingService.bookTicket(cinema.getSeatList().get(row - 1).get(col - 1));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Wrong input!");
                        repeat = true;
                        break;
                    } catch (SeatAlreadyBookedException e) {
                        System.out.println("That ticket has already been purchased!");
                        repeat = true;
                        break;
                    }
                    repeat = false;
                    //cinemaService.viewSeats();
                    System.out.println();
                    break;
                case 3:
                    cinemaService.updateStatistics();
                    cinemaService.getPurchasedTicketCount();
                    cinemaService.getOccupancyPercent();
                    cinemaService.getCurrentIncome();
                    cinemaService.getTotalIncome();
                    System.out.println();
                    break;
                case 0:
                    active = false;
                    break;
            }
        }
    }
}