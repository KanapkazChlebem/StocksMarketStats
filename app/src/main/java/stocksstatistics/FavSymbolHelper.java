package stocksstatistics;

public class FavSymbolHelper {
    private String favSymbol=null;
    private int favCounter=0;

    public FavSymbolHelper(String favSymbol, int favCounter) {
        this.favSymbol = favSymbol;
        this.favCounter = favCounter;
    }

    public FavSymbolHelper() {
    }

    public String getFavSymbol() {
        return favSymbol;
    }

    public void setFavSymbol(String favSymbol) {
        this.favSymbol = favSymbol;
    }

    public int getFavCounter() {
        return favCounter;
    }

    public void setFavCounter(int favCounter) {
        this.favCounter = favCounter;
    }
}
