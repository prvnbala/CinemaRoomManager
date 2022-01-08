package cinema.models;

public class Seat {
    private char status;
    private int price;

    public Seat(char status, int price) {
        this.status = status;
        this.price = price;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
