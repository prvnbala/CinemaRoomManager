package cinema.strategies;

import cinema.strategies.PricingStrategy;

public class CinemaSizeBasedPricingStrategy implements PricingStrategy {
    private int rows;
    private int cols;
    private int totalCount;

    private final int frontHalfPrice = 10;
    private final int rearHalfPrice = 8;

    public CinemaSizeBasedPricingStrategy(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        totalCount = rows * cols;
    }

    public int getSalesEstimate() {
        int sales = 0;
        if (rows * cols <= 60) {
            sales = rows * cols * frontHalfPrice;
        } else {
            sales += rows / 2 * cols * frontHalfPrice;
            sales += (rows - rows / 2) * cols * rearHalfPrice;
        }

        return sales;
    }

    public int getSeatPrice(int row, int col) {
        if (totalCount <= 60) {
            return 10;
        }

        if (row + 1 <= rows / 2) {
            return frontHalfPrice;
        } else {
            return rearHalfPrice;
        }
    }
}
