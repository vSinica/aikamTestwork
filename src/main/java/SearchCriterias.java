import java.util.ArrayList;

public class SearchCriterias {

    private ArrayList<String> lastNames = new ArrayList<>();

    private ArrayList<String> productNames = new ArrayList<>();
    private ArrayList<Integer> minTimes = new ArrayList<>();

    private ArrayList<Integer> minExpenses = new ArrayList<>();
    private ArrayList<Integer> maxExpenses = new ArrayList<>();

    private ArrayList<Integer> badCustomers = new ArrayList<>();


    public ArrayList<Integer> getBadCustomers() {
        return badCustomers;
    }

    public void addBadCustomer(Integer badCustomer) {
        this.badCustomers.add(badCustomer);
    }

    public ArrayList<Integer> getMinExpenses() {
        return minExpenses;
    }

    public void addMinExpenses(Integer minExpenses) {
        this.minExpenses.add(minExpenses);
    }

    public ArrayList<Integer> getMaxExpenses() {
        return maxExpenses;
    }

    public void addMaxExpenses(Integer maxExpenses) {
        this.maxExpenses.add(maxExpenses);
    }

    public ArrayList<Integer> getMinTimes() {
        return minTimes;
    }

    public void addMinTime(Integer minTimes) {
        this.minTimes.add(minTimes);
    }

    public ArrayList<String> getProductNames() {
        return productNames;
    }

    public void addProductName(String productName) {
        this.productNames.add(productName);
    }

    public ArrayList<String> getLastNames() {
        return lastNames;
    }

    public void addLastName(String lastName) {
        this.lastNames.add(lastName);
    }
}
