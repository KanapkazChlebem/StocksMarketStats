package stocksstatistics;

import java.text.DecimalFormat;
import java.time.Period;
import java.util.NoSuchElementException;

public class EmployeeStatistics {
    private String favSymbol;
    private int favSymbolCounter;
    private Period maxHoldProfit;
    private Period maxHoldLoss;
    private Period avgHoldProfit;
    private Period avgHoldLoss;
    private int maxSequenceProfits;
    private double avgSequenceProfits;
    private int maxSequenceLoss;
    private double avgSequenceLoss;
    private double maxProfitTransactions;
    private double avgProfitTransactions;
    private double maxLossTransactions;
    private double avgLossTransactions;
    private double totalTransactions;
    private int lostLessTransactions;//
    private double percentProfitTransactions;
    private double percentBuyProfit;
    private double percentSellProfit;
    private double TotalProfitOrLoss;
    private double avgProfitOrLossOverTime;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    @Override
    public String toString() {
        return "EmployeeStatistics{" +
                "favSymbol='" + favSymbol + '\'' +
                ", favSymbolCounter=" + favSymbolCounter +
                ", maxHoldProfit=" + maxHoldProfit.getDays() + " Days" +
                ", maxHoldLoss=" + maxHoldLoss.getDays() + " Days" +
                ", avgHoldProfit=" + avgHoldProfit.getDays() + " Days" +
                ", avgHoldLoss=" + avgHoldLoss.getDays() + " Days" +
                ", maxSequenceProfits=" + maxSequenceProfits +
                ", avgSequenceProfits=" + decfor.format(avgSequenceProfits) +
                ", maxSequenceLoss=" + maxSequenceLoss +
                ", avgSequenceLoss=" + decfor.format(avgSequenceLoss) +
                ", maxProfitTransactions=" + maxProfitTransactions +
                ", avgProfitTransactions=" + decfor.format(avgProfitTransactions) +
                ", maxLossTransactions=" + decfor.format(maxLossTransactions) +
                ", avgLossTransactions=" + decfor.format(avgLossTransactions) +
                ", totalTransactions=" + totalTransactions +
                ", lostLessTransactions=" + lostLessTransactions +
                ", percentProfitTransactions=" + decfor.format(percentProfitTransactions * 100) + "%" +
                ", percentBuyProfit=" + decfor.format(percentBuyProfit * 100) + "%" +
                ", percentSellProfit=" + decfor.format(percentSellProfit * 100) + "%" +
                ", TotalProfitOrLoss=" + decfor.format(TotalProfitOrLoss) +
                ", avgProfitOrLossOverTime=" + decfor.format(avgProfitOrLossOverTime)+" per day" +
                '}';
    }

    public EmployeeStatistics(String favSymbol, int favSymbolCounter, Period maxHoldProfit, Period maxHoldLoss, Period avgHoldProfit, Period avgHoldLoss, int maxSequenceProfits, double avgSequenceProfits, int maxSequenceLoss, double avgSequenceLoss, double maxProfitTransactions, double avgProfitTransactions, double maxLossTransactions, double avgLossTransactions, double totalTransactions, int lostLessTransactions, Double percentProfitTransactions, Double percentBuyProfit, double percentSellProfit, double totalProfitOrLoss, double avgProfitOrLossOverTime) {
        this.favSymbol = favSymbol;
        this.favSymbolCounter = favSymbolCounter;
        this.maxHoldProfit = maxHoldProfit;
        this.maxHoldLoss = maxHoldLoss;
        this.avgHoldProfit = avgHoldProfit;
        this.avgHoldLoss = avgHoldLoss;
        this.maxSequenceProfits = maxSequenceProfits;
        this.avgSequenceProfits = avgSequenceProfits;
        this.maxSequenceLoss = maxSequenceLoss;
        this.avgSequenceLoss = avgSequenceLoss;
        this.maxProfitTransactions = maxProfitTransactions;
        this.avgProfitTransactions = avgProfitTransactions;
        this.maxLossTransactions = maxLossTransactions;
        this.avgLossTransactions = avgLossTransactions;
        this.totalTransactions = totalTransactions;
        this.lostLessTransactions = lostLessTransactions;
        this.percentProfitTransactions = percentProfitTransactions;
        this.percentBuyProfit = percentBuyProfit;
        this.percentSellProfit = percentSellProfit;
        TotalProfitOrLoss = totalProfitOrLoss;
        this.avgProfitOrLossOverTime = avgProfitOrLossOverTime;
    }

    public String getFavSymbol() {
        return favSymbol;
    }

    public void setFavSymbol(String favSymbol) {
        this.favSymbol = favSymbol;
    }

    public int getFavSymbolCounter() {
        return favSymbolCounter;
    }

    public void setFavSymbolCounter(int favSymbolCounter) {
        this.favSymbolCounter = favSymbolCounter;
    }

    public Period getMaxHoldProfit() {
        return maxHoldProfit;
    }

    public void setMaxHoldProfit(Period maxHoldProfit) {
        this.maxHoldProfit = maxHoldProfit;
    }

    public Period getMaxHoldLoss() {
        return maxHoldLoss;
    }

    public void setMaxHoldLoss(Period maxHoldLoss) {
        this.maxHoldLoss = maxHoldLoss;
    }

    public Period getAvgHoldProfit() {
        return avgHoldProfit;
    }

    public void setAvgHoldProfit(Period avgHoldProfit) {
        this.avgHoldProfit = avgHoldProfit;
    }

    public Period getAvgHoldLoss() {
        return avgHoldLoss;
    }

    public void setAvgHoldLoss(Period avgHoldLoss) {
        this.avgHoldLoss = avgHoldLoss;
    }


    public int getMaxSequenceProfits() {
        return maxSequenceProfits;
    }

    public void setMaxSequenceProfits(int maxSequenceProfits) {
        this.maxSequenceProfits = maxSequenceProfits;
    }

    public double getAvgSequenceProfits() {
        return avgSequenceProfits;
    }

    public void setAvgSequenceProfits(double avgSequenceProfits) {
        this.avgSequenceProfits = avgSequenceProfits;
    }

    public int getMaxSequenceLoss() {
        return maxSequenceLoss;
    }

    public void setMaxSequenceLoss(int maxSequenceLoss) {
        this.maxSequenceLoss = maxSequenceLoss;
    }

    public double getAvgSequenceLoss() {
        return avgSequenceLoss;
    }

    public void setAvgSequenceLoss(double avgSequenceLoss) {
        this.avgSequenceLoss = avgSequenceLoss;
    }

    public double getMaxProfitTransactions() {
        return maxProfitTransactions;
    }

    public void setMaxProfitTransactions(double maxProfitTransactions) {
        this.maxProfitTransactions = maxProfitTransactions;
    }

    public double getAvgProfitTransactions() {
        return avgProfitTransactions;
    }

    public void setAvgProfitTransactions(double avgProfitTransactions) {
        this.avgProfitTransactions = avgProfitTransactions;
    }

    public double getMaxLossTransactions() {
        return maxLossTransactions;
    }

    public void setMaxLossTransactions(double maxLossTransactions) {
        this.maxLossTransactions = maxLossTransactions;
    }

    public double getAvgLossTransactions() {
        return avgLossTransactions;
    }

    public void setAvgLossTransactions(double avgLossTransactions) {
        this.avgLossTransactions = avgLossTransactions;
    }

    public double getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(double totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public double getPercentProfitTransactions() {
        return percentProfitTransactions;
    }

    public void setPercentProfitTransactions(double percentProfitTransactions) {
        this.percentProfitTransactions = percentProfitTransactions;
    }

    public double getPercentBuyProfit() {
        return percentBuyProfit;
    }

    public void setPercentBuyProfit(double percentBuyProfit) {
        this.percentBuyProfit = percentBuyProfit;
    }

    public double getPercentSellProfit() {
        return percentSellProfit;
    }

    public void setPercentSellProfit(double percentSellProfit) {
        this.percentSellProfit = percentSellProfit;
    }

    public double getTotalProfitOrLoss() {
        return TotalProfitOrLoss;
    }

    public void setTotalProfitOrLoss(double totalProfitOrLoss) {
        TotalProfitOrLoss = totalProfitOrLoss;
    }

    public double getAvgProfitOrLossOverTime() {
        return avgProfitOrLossOverTime;
    }

    public void setAvgProfitOrLossOverTime(double avgProfitOrLossOverTime) {
        this.avgProfitOrLossOverTime = avgProfitOrLossOverTime;
    }

    public int getLostLessTransactions() {
        return lostLessTransactions;
    }

    public void setLostLessTransactions(int lostLessTransactions) {
        this.lostLessTransactions = lostLessTransactions;
    }
}
