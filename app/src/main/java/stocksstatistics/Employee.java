package stocksstatistics;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Employee {

    private EmployeeStatistics stats;
    private int minCapacity = 16;
    private List<Operation> operationList = new ArrayList<>(minCapacity);
    private String firstName;
    private String lastName;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH);

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EmployeeStatistics getStats() {
        return stats;
    }

    public void setStats(EmployeeStatistics stats) {
        this.stats = stats;
    }

    public void addOperationToList(Operation operation) {
        operationList.add(operation);
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void calculateStatisticsFromHistory() {
        List<String> types = new ArrayList<>();
        Period maxHoldProfit = Period.ofDays(0);//Period maxHoldProfit
        Period maxHoldLoss = Period.ofDays(0);//Period maxHoldLoss
        List<Period> holdTimesProfit = new ArrayList<>();//Period avgHoldProfit
        List<Period> holdTimesLoss = new ArrayList<>();//Period avgHoldLoss
        int lostLessTransactions = 0;
        //sequences
        int profitSeqCounter = 0;
        int lossSeqCounter = 0;
        int maxSequenceProfit = 0;
        int maxSequenceLoss = 0;
        boolean wasLastLoss = false;
        List<Integer> profitSeqList = new ArrayList<>();
        List<Integer> lossSeqList = new ArrayList<>();
        //end sequences
        double maxProfit = 0;
        List<Double> profits = new ArrayList<>();
        double maxLoss = 0;
        List<Double> losses = new ArrayList<>();
        double buyProfCounter = 0;
        double sellProfCounter = 0;
        double totalProfitOrLoss = 0;
        LocalDate startDate = LocalDate.parse(operationList.get(0).getOpenTime(), timeFormatter);
        Period daysOfWork = Period.between(startDate, LocalDate.now());
        for (Operation operation : operationList
        ) {
            Period period = getHoldTime(operation.getOpenTime(), operation.getCloseTime());//period of operation
            types.add(operation.getType());//to determine favorite symbol of operation
            totalProfitOrLoss += operation.getProfitOrLoss();
            if (operation.getProfitOrLoss() > 0) {
                profitSeqCounter++;
                if (operation.getType().contains("Buy")) buyProfCounter++;
                else sellProfCounter++;
                profits.add(operation.getProfitOrLoss());
                if (maxProfit < operation.getProfitOrLoss()) maxProfit = operation.getProfitOrLoss();
                if (wasLastLoss == false) {
                    if (profitSeqCounter > maxSequenceProfit) maxSequenceProfit = profitSeqCounter;
                } else {
                    wasLastLoss = false;
                    if (lossSeqCounter != 0) lossSeqList.add(lossSeqCounter);
                    lossSeqCounter = 0;
                    if (profitSeqCounter > maxSequenceProfit) maxSequenceProfit = profitSeqCounter;
                }//sequences
                holdTimesProfit.add(period);//Period avgHoldProfit
                if (maxHoldProfit.getDays() < period.getDays()) //Period maxHoldProfit
                    maxHoldProfit = period;
            } else if (operation.getProfitOrLoss() < 0) {
                lossSeqCounter++;//sequenced losses
                losses.add(operation.getProfitOrLoss());//all losses
                if (maxLoss > operation.getProfitOrLoss()) maxLoss = operation.getProfitOrLoss();
                if (wasLastLoss == true) {
                    if (lossSeqCounter > maxSequenceLoss) maxSequenceLoss = lossSeqCounter;
                } else {
                    wasLastLoss = true;
                    if (profitSeqCounter != 0) profitSeqList.add(profitSeqCounter);
                    profitSeqCounter = 0;
                    if (lossSeqCounter > maxSequenceLoss) maxSequenceLoss = lossSeqCounter;
                }//sequences
                holdTimesLoss.add(period); //Period avgHoldLoss
                if (maxHoldLoss.getDays() < period.getDays()) //Period maxHoldLoss
                    maxHoldLoss = period;
            } else lostLessTransactions++;
            //
        }
        if (profitSeqCounter != 0) profitSeqList.add(profitSeqCounter);
        if (lossSeqCounter != 0) lossSeqList.add(lossSeqCounter);
        Period avgHoldProfit = avgOfHoldPeriods(holdTimesProfit);
        Period avgHoldLoss = avgOfHoldPeriods(holdTimesLoss);
        double profitOrLossOverTime = totalProfitOrLoss / daysOfWork.getDays();
        double percentOfBuy = buyProfCounter / types.size();
        double percentOfSell = sellProfCounter / types.size();
        double totalTransactions = losses.size() + profits.size() + lostLessTransactions;
        double profitTransactions = profits.size();
        double percentProfitTransactions = profitTransactions / totalTransactions;
        //avg of Profits and losses
        double avgProf = getAvgOfProfitsOrLosses(profits);
        double avgLoss = getAvgOfProfitsOrLosses(losses);
        //avg of sequences
        double avgSeqProf = getAvgOfSequences(profitSeqList);
        double avgSeqLoss = getAvgOfSequences(lossSeqList);
        //determining favorite symbol of operation
        FavSymbolHelper favSymbolHelper = favSymbolAndCounterProvider(types);//favorite symbol and counter
        stats = new EmployeeStatistics(favSymbolHelper.getFavSymbol(), favSymbolHelper.getFavCounter(), maxHoldProfit, maxHoldLoss,
                avgHoldProfit, avgHoldLoss, maxSequenceProfit, avgSeqProf, maxSequenceLoss, avgSeqLoss,
                maxProfit, avgProf, maxLoss, avgLoss, totalTransactions, lostLessTransactions,
                percentProfitTransactions, percentOfBuy, percentOfSell, totalProfitOrLoss, profitOrLossOverTime);
    }

    private FavSymbolHelper favSymbolAndCounterProvider(List<String> types) {//determining favorite symbol and counter of operation
        HashSet<String> typesSet = new HashSet<>();
        typesSet.addAll(types);
        FavSymbolHelper favSymbolHelper = new FavSymbolHelper();
        for (String name : typesSet
        ) {
            if (favSymbolHelper.getFavCounter() < Collections.frequency(types, name)) {
                favSymbolHelper.setFavCounter(Collections.frequency(types, name));
                favSymbolHelper.setFavSymbol(name);
            }
        }
        return favSymbolHelper;
    }


    private Period maxHoldProfitProvider() {
        return null;
    }

    private Period maxHoldLossProvider() {
        return null;
    }

    private Period avgHoldProfitProvider() {
        return null;
    }

    private Period avgHoldLossProvider() {
        return null;
    }

    private int maxSequenceProfitsProvider() {
        return -1;
    }

    private double avgSequenceProfitsProvider() {
        return -1;
    }

    private int maxSequenceLossProvider() {
        return -1;
    }

    private double avgSequenceLossProvider() {
        return -1;
    }

    private double maxProfitTransactionsProvider() {
        return -1;
    }

    private double avgProfitTransactionsProvider() {
        return -1;
    }

    private double maxLossTransactionsProvider() {
        return -1;
    }

    private double avgLossTransactionsProvider() {
        return -1;
    }

    private int totalTransactionsProvider() {
        return -1;
    }

    private int lostLessTransactionsProvider() {
        return -1;
    }

    private double percentProfitTransactionsProvider() {
        return -1;
    }

    private double percentBuyProfitProvider() {
        return -1;
    }

    private double percentSellProfitProvider() {
        return -1;
    }

    private double TotalProfitOrLossProvider() {
        return -1;
    }

    private double avgProfitOrLossOverTimeProvider() {
        return -1;
    }

    private Period avgOfHoldPeriods(List<Period> periods) {
        Period avg = Period.ofDays(0);
        for (Period period : periods
        ) {
            avg = Period.ofDays(period.getDays() + avg.getDays());
        }
        return Period.ofDays(avg.getDays() / periods.size());
    }

    private double getAvgOfSequences(List<Integer> list) {
        double avg = 0;
        for (int element : list
        )
            avg += element;
        return avg / list.size();
    }

    private double getAvgOfProfitsOrLosses(List<Double> list) {
        double avg = 0;
        for (double element : list
        )
            avg += element;
        return avg / list.size();
    }

    private Period getHoldTime(String openTime, String closeTime) {
        LocalDate start = LocalDate.parse(openTime, timeFormatter);
        LocalDate end = LocalDate.parse(closeTime, timeFormatter);
        return Period.between(start, end);
    }
}

