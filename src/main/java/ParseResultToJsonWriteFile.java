import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ParseResultToJsonWriteFile {

    private ExecuteQueries executeQueries;

    private SearchCriterias searchCriterias;
    private StatCriterias statCriterias;

    private List<Map<String,String>> manyPresentCustomer = new LinkedList<>();

    public ParseResultToJsonWriteFile(ExecuteQueries executeQueries) {
        this.executeQueries = executeQueries;
    }

    public void parseWriteToFileStat() throws IOException {

        List<List<Map<String,String>>> convertedcustomer = new ArrayList<>();
        for (Map<String, String> results : executeQueries.getStatResults()) {

            boolean isthiscustomerhaveinfinallist = false;
            for (List<Map<String, String>> listwithonecustomerdata : convertedcustomer) {
                for (Map<String, String> customer : listwithonecustomerdata) {
                    if (customer.containsValue(results.get("firstName")) &&
                            customer.containsValue(results.get("firstName"))) {
                        isthiscustomerhaveinfinallist = true;
                    }
                }
            }

            boolean isthiscustomermanypresent = false;
            for (Map<String, String> customer : executeQueries.getStatResults()) {
                if (customer.containsValue(results.get("firstName")) &&
                        customer.containsValue(results.get("firstName")) &&
                        !customer.equals(results)) {
                    isthiscustomermanypresent = true;
                }
            }

            if (isthiscustomerhaveinfinallist && isthiscustomermanypresent) continue;

            List<Map<String, String>> onecustomeralldatalist = new ArrayList<>();
            if (!isthiscustomerhaveinfinallist && isthiscustomermanypresent) {

                for (Map<String, String> res : executeQueries.getStatResults()) {
                    Map<String, String> tempmap = new HashMap<>();
                    if (res.containsValue(results.get("firstName")) && res.containsValue(results.get("lastName"))) {
                        tempmap.put("firstName", res.get("firstName"));
                        tempmap.put("lastName", res.get("lastName"));
                        tempmap.put("ProductName", res.get("ProductName"));
                        tempmap.put("sum", res.get("sum"));
                        onecustomeralldatalist.add(tempmap);
                    }
                }
                convertedcustomer.add(onecustomeralldatalist);
                continue;
            }
            Map<String, String> tempmap = new HashMap<>();
            tempmap.put("firstName", results.get("firstName"));
            tempmap.put("lastName", results.get("lastName"));
            tempmap.put("ProductName", results.get("ProductName"));
            tempmap.put("sum", results.get("sum"));
            onecustomeralldatalist.add(tempmap);
            convertedcustomer.add(onecustomeralldatalist);
        }

        Map<String,Object> totalresult = new HashMap<>();
        totalresult.put("type","stat");

        long intervalInMilis = statCriterias.getEndDate().getTime()-statCriterias.getStartDate().getTime();
        long days = (intervalInMilis / (60*60*24*1000));
        totalresult.put("totalDays",days);

        List<Map<String,Object>> customersjsonblock = new ArrayList<>();

        Integer alltotlaexpenses = 0;
        for (List<Map<String,String>> listcustomerdata : convertedcustomer){
            Integer totalexpenses = 0;
            List<Map<String,Object>> purchases = new ArrayList<>();
            for(Map<String,String> customerdata : listcustomerdata){
                Map<String,Object> customerpurchase = new HashMap<>();
                customerpurchase.put("name",customerdata.get("ProductName"));
                customerpurchase.put("expenses",customerdata.get("sum"));
                purchases.add(customerpurchase);
                totalexpenses+=Integer.parseInt(customerdata.get("sum"));
            }

            Map<String,Object> purchasesandname = new HashMap<>();
            purchasesandname.put("name", listcustomerdata.get(0).get("firstName")+" "+listcustomerdata.get(0).get("lastName"));
            purchasesandname.put("purchases",purchases);
            purchasesandname.put("totalExpenses",totalexpenses);
            alltotlaexpenses+=totalexpenses;
            customersjsonblock.add(purchasesandname);
        }

        totalresult.put("customers",customersjsonblock);

        totalresult.put("totalExpenses",alltotlaexpenses);
        totalresult.put("avgExpenses",alltotlaexpenses/convertedcustomer.size());

        String outputjson = (new JSONObject(totalresult)).toString(1);
        Path file = Paths.get("output.json");
        Files.write(file, Collections.singleton(outputjson), StandardCharsets.UTF_8);
    }

    public void parseWriteToFileSearch() throws IOException {
        Map<String,Object> totalresult = new HashMap<>();
        totalresult.put("type","search");

        List<Object> resultcriterialist = new ArrayList<>();


        List<Map<String,Object>> lastnameresultslist = new ArrayList<>();

        for (int i=0;i<searchCriterias.getLastNames().size();i++) {
            Map<String,Object> lastnamesoutput = new TreeMap<>();
            HashMap<String,String> criteriamap = new HashMap<String,String>();
            criteriamap.put("lastName",searchCriterias.getLastNames().get(i));
            lastnamesoutput.put("criteria",criteriamap);
            lastnamesoutput.put("results",executeQueries.getLastNamesResults().get(i));
            lastnameresultslist.add(lastnamesoutput);
        }
        resultcriterialist.add(lastnameresultslist);


        List<Map<String,Object>> mintimesresultslist = new ArrayList<>();

        for (int i=0;i<searchCriterias.getMinTimes().size();i++) {
            Map<String,Object> minTimesoutput = new TreeMap<>();
            HashMap<String,String> criteriamap = new HashMap<String,String>();
            criteriamap.put("productName",searchCriterias.getProductNames().get(i));
            criteriamap.put("minTimes",Integer.toString(searchCriterias.getMinTimes().get(i)));
            minTimesoutput.put("criteria",criteriamap);
            minTimesoutput.put("results",executeQueries.getMinTimesResults().get(i));
            mintimesresultslist.add(minTimesoutput);
        }
        resultcriterialist.add(mintimesresultslist);


        List<Map<String,Object>> minmaxexpensesresultslist = new ArrayList<>();

        for (int i=0;i<searchCriterias.getMinExpenses().size();i++) {
            Map<String,Object> minmaxexpensesoutput = new TreeMap<>();
            HashMap<String,String> criteriamap = new HashMap<String,String>();
            criteriamap.put("minExpenses",Integer.toString(searchCriterias.getMinExpenses().get(i)));
            criteriamap.put("maxExpenses",Integer.toString(searchCriterias.getMaxExpenses().get(i)));
            minmaxexpensesoutput.put("criteria",criteriamap);
            minmaxexpensesoutput.put("results",executeQueries.getMinMaxExpensesResults().get(i));
            minmaxexpensesresultslist.add(minmaxexpensesoutput);
        }
        resultcriterialist.add(minmaxexpensesresultslist);


        List<Map<String,Object>> badcustomersresultslist = new ArrayList<>();

        for (int i=0;i<searchCriterias.getBadCustomers().size();i++) {
            Map<String,Object> badcustomersoutput = new TreeMap<>();
            HashMap<String,String> criteriamap = new HashMap<String,String>();
            criteriamap.put("badCustomers",Integer.toString(searchCriterias.getBadCustomers().get(i)));
            badcustomersoutput.put("criteria",criteriamap);
            badcustomersoutput.put("results",executeQueries.getBadCustomersResults().get(i));
            badcustomersresultslist.add(badcustomersoutput);
        }
        resultcriterialist.add(badcustomersresultslist);


        totalresult.put("results",resultcriterialist);
        System.out.println(totalresult);

        String outputjson = (new JSONObject(totalresult)).toString(1);
        Path file = Paths.get("output.json");
        Files.write(file, Collections.singleton(outputjson), StandardCharsets.UTF_8);
    }

    public void setSearchCriterias(SearchCriterias searchCriterias) {
        this.searchCriterias = searchCriterias;
    }

    public void setStatCriterias(StatCriterias statCriterias) {
        this.statCriterias = statCriterias;
    }
}
