package Models;

public class Counter {

    public String counterTitle;
    public int total;

    public Counter(String counterTitle, int total) {
        this.counterTitle = counterTitle;
        this.total = total;
    }

    public String getCounterTitle() {
        return counterTitle;
    }

    public void setCounterTitle(String counterTitle) {
        this.counterTitle = counterTitle;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
