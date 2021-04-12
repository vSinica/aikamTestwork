
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;

public class App {


    public static void main(String[] args) throws IOException, SQLException {

        if (args.length < 3) {
            System.out.println("Ќеправильное кол-во аргументов");
            return;
        }
        if (args[0].isEmpty()) {
            System.out.println("пустое название операции");
            return;
        }
        if (!args[0].equals("search") && !args[0].equals("stat")) {
            System.out.println("Ќеправильное название операции");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/aikam";
        String login = "postgres";
        String password = "vados";

        if (args[0].equals("search")) {
            ParseInputFile parseInputFile = new ParseInputFile(args[1]);
            SearchCriterias searchCriterias = parseInputFile.parseSearchCriterias();

            ExecuteQueries executeQueries = new ExecuteQueries(url, login, password);
            executeQueries.setSearchCriterias(searchCriterias);
            executeQueries.getSearchQueryResult();

            ParseResultToJsonWriteFile parseResultToJsonWriteFile = new ParseResultToJsonWriteFile(executeQueries);
            parseResultToJsonWriteFile.setSearchCriterias(searchCriterias);
            parseResultToJsonWriteFile.parseWriteToFileSearch();
        }

        if (args[0].equals("stat")) {
            ParseInputFile parseInputFile = new ParseInputFile(args[1]);
            StatCriterias statCriterias = parseInputFile.parseStatCriteries();

            ExecuteQueries executeQueries = new ExecuteQueries(url, login, password);
            executeQueries.setStatCriterias(statCriterias);
            executeQueries.getStatQueryResult();

            ParseResultToJsonWriteFile parseResultToJsonWriteFile = new ParseResultToJsonWriteFile(executeQueries);
            parseResultToJsonWriteFile.setStatCriterias(statCriterias);
            parseResultToJsonWriteFile.parseWriteToFileStat();


        }
    }
}
