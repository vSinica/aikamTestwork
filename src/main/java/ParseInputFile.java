import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;

public class ParseInputFile {

    private SearchCriterias searchCriterias;
    private String inputFilePath;

    public ParseInputFile(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public SearchCriterias parseSearchCriterias() throws IOException {

        String jsonString = new String(Files.readAllBytes(Paths.get(inputFilePath)));

        JSONObject criteriasObject = new JSONObject(jsonString);
        JSONArray criterias = criteriasObject.getJSONArray("criterias");

        SearchCriterias searchCriterias = new SearchCriterias();

        for (Object item: criterias) {
            JSONObject criteria = (JSONObject)item;

            if(!criteria.optString("lastName").equals("")) {
                searchCriterias.addLastName(criteria.getString("lastName"));
            }

            if(!criteria.optString("productName").equals("")) {
                searchCriterias.addProductName(criteria.getString("productName"));

            }

            if(criteria.optInt("minTimes")!=0) {
                searchCriterias.addMinTime(criteria.getInt("minTimes"));
            }

            if(criteria.optInt("minExpenses")!=0) {
                searchCriterias.addMinExpenses(criteria.getInt("minExpenses"));
            }

            if(criteria.optInt("maxExpenses")!=0) {
                searchCriterias.addMaxExpenses(criteria.getInt("maxExpenses"));
            }

            if(criteria.optInt("badCustomers")!=0) {
                searchCriterias.addBadCustomer(criteria.getInt("badCustomers"));
            }
        }

        return searchCriterias;

    }

    public StatCriterias parseStatCriteries() throws IOException {

        String jsonString = new String(Files.readAllBytes(Paths.get(inputFilePath)));

        JSONObject criterias = new JSONObject(jsonString);
        StatCriterias statCriterias = new StatCriterias();

        if(!criterias.optString("startDate").equals("")) {
                Date date = Date.valueOf(criterias.getString("startDate"));
                statCriterias.setStartDate(date);
        }

        if(!criterias.optString("endDate").equals("")) {
            Date date = Date.valueOf(criterias.getString("endDate"));
            statCriterias.setEndDate(date);
        }

        return statCriterias;
    }
}
