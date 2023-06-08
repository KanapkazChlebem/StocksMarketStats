package stocksstatistics;

import java.io.Serializable;
import java.util.Date;

public class Operation implements Serializable {
    private String symbol;
    private String type;
    private String openTime;
    private String closeTime;
    private double ProfitOrLoss;

    public Operation(String symbol, String type, String openTime, String closeTime, double profitOrLoss) {
        this.symbol = symbol;
        this.type = type;
        this.openTime = openTime;
        this.closeTime = closeTime;
        ProfitOrLoss = profitOrLoss;
    }

    public Operation() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public double getProfitOrLoss() {
        return ProfitOrLoss;
    }
}
