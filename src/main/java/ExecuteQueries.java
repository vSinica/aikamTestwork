import java.sql.*;
import java.util.*;

public class ExecuteQueries {

    private String url;
    private String login;
    private String password;

    private SearchCriterias searchCriterias;
    private StatCriterias statCriterias;

    private List<List<Map<String,String>>> lastNamesResults = new ArrayList<>();
    private List<List<Map<String,String>>> minTimesResults = new ArrayList<>();
    private List<List<Map<String,String>>> minMaxExpensesResults = new ArrayList<>();
    private List<List<Map<String,String>>> badCustomersResults = new ArrayList<>();

    private List<Map<String,String>> statResults = new ArrayList<>();


    public ExecuteQueries(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    private List<Map<String,String>> getSearchFromResultSet(ResultSet resultSet) throws SQLException {
        List<Map<String,String>> thisQueryResult = new LinkedList<>();

        if (!resultSet.next()){
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("not found", "for this criteria");
            thisQueryResult.add(tempMap);
        }

        while (resultSet.next()) {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("firstName", resultSet.getString("first_name"));
            tempMap.put("lastName", resultSet.getString("last_name"));

            thisQueryResult.add(tempMap);
        }

        return thisQueryResult;
    }

    public void getStatQueryResult() throws SQLException {

        Connection conn = DriverManager.getConnection(url, login, password);
        Statement stmt = conn.createStatement();

        ResultSet resultSet = stmt.executeQuery("SELECT c.first_name,c.last_name,sum(prod.cost) as sum,prod.name\n" +
                "FROM purchases p join product prod\n" +
                "on p.product_id = prod.id join customer c\n" +
                "on p.customer_id = c.id\n" +
                "where p.date between '"+ statCriterias.getStartDate() +"' and '"+ statCriterias.getEndDate() +"'\n" +
                "group by c.last_name,prod.cost,prod.name,c.first_name order by sum desc");

        List<Map<String,String>> thisQueryResult = new LinkedList<>();

        while (resultSet.next()) {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("firstName", resultSet.getString("first_name"));
            tempMap.put("lastName", resultSet.getString("last_name"));
            tempMap.put("sum", resultSet.getString("sum"));
            tempMap.put("ProductName", resultSet.getString("name"));
            thisQueryResult.add(tempMap);
        }
        statResults=thisQueryResult;
    }

    public void getSearchQueryResult() throws SQLException {

        Connection conn = DriverManager.getConnection(url, login, password);
        Statement stmt = conn.createStatement();

        for (int i=0;i<searchCriterias.getLastNames().size();i++) {

            ResultSet resultSet = stmt.executeQuery("SELECT first_name, last_name FROM public.customer\n" +
                    " where last_name = '" + searchCriterias.getLastNames().get(i) + "';");

            lastNamesResults.add(getSearchFromResultSet(resultSet));
        }

        for (int i=0;i<searchCriterias.getProductNames().size();i++) {

            ResultSet resultSet = stmt.executeQuery(
                    "SELECT c.last_name, c.first_name \n" +
                            "\tFROM customer c join purchases p \n" +
                            "\ton c.id = p.customer_id join product prod\n" +
                            "\ton p.product_id = prod.id \n" +
                            "\twhere prod.name = '"+searchCriterias.getProductNames().get(i)+"'\n" +
                            "\tgroup by c.id\n" +
                            "\thaving count(prod.name) > "+ searchCriterias.getMinTimes().get(i) +";");

            minTimesResults.add(getSearchFromResultSet(resultSet));
        }

        for (int i=0;i<searchCriterias.getMinExpenses().size();i++) {

            ResultSet resultSet = stmt.executeQuery(
                    "SELECT c.first_name,c.last_name,sum(prod.cost) from purchases p \n" +
                            " join customer c on p.customer_id = c.id\n" +
                            " join product prod on p.product_id = prod.id\n" +
                            " group by c.id \n" +
                            " having sum(prod.cost) between "+ searchCriterias.getMinExpenses().get(i) +" " +
                            "and "+ searchCriterias.getMaxExpenses().get(i) +" ");

            minMaxExpensesResults.add(getSearchFromResultSet(resultSet));
        }

        for (int i=0;i<searchCriterias.getBadCustomers().size();i++) {

            ResultSet resultSet = stmt.executeQuery(
                    "sELECT c.first_name,c.last_name from purchases p \n" +
                            "join customer c on p.customer_id = c.id\n" +
                            "group by c.id \n" +
                            "limit " + searchCriterias.getBadCustomers().get(i) + "");

            badCustomersResults.add(getSearchFromResultSet(resultSet));
        }
    }

    public List<List<Map<String, String>>> getLastNamesResults() {
        return lastNamesResults;
    }

    public List<List<Map<String, String>>> getMinTimesResults() {
        return minTimesResults;
    }

    public List<List<Map<String, String>>> getMinMaxExpensesResults() {
        return minMaxExpensesResults;
    }

    public List<List<Map<String, String>>> getBadCustomersResults() {
        return badCustomersResults;
    }

    public void setSearchCriterias(SearchCriterias searchCriterias) {
        this.searchCriterias = searchCriterias;
    }

    public void setStatCriterias(StatCriterias statCriterias) {
        this.statCriterias = statCriterias;
    }

    public List<Map<String, String>> getStatResults() {
        return statResults;
    }
}
