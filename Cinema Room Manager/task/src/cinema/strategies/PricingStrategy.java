package cinema.strategies;

public interface PricingStrategy {
    public int getSalesEstimate();
    public int getSeatPrice(int row, int col);
}
