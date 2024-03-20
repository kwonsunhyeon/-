package seoulbus;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static java.lang.Thread.sleep;

public class CsvFileSaveToDB {
    private final static String fliePath = "C:/java-sql/2021_seoul_bus_onoff.csv";


    public static void main(String[] args) {
        final String jdbcURL = "jdbc:mysql://localhost:3306/yscom";
        final String username = "root";
        final String password = "1234";

        final int batchSize = 2_000; // bulk insert

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("연결됨");

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        StringBuffer sb = new StringBuffer("null");

        for (int i = 1; i <= 55; i++) {
            sb.append(",? ");
        }
        sb.setLength((sb.length() - 1));

        String sql = "insert into tbl_seoul_bus_onoff values (" + sb.toString() + ");";
        System.out.println(sql);

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int columSize = 55;


        List<CSVRecord> records = null;

        try {
            records = getCsvRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int row = 0; row < records.size(); row++) {
            CSVRecord data = records.get(row);
            for (int fieldIndex = 0; fieldIndex < columSize; fieldIndex++) {

                try {
                    statement.setString(fieldIndex + 1, data.get(fieldIndex));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                statement.addBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (row % batchSize == 0) {
                try {
                    statement.executeBatch();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("statement.executeBatch() ing row ==> %s", row));
                try {
                    connection.commit();// db에 적용시킨다
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                sleep(1);
            }
        }//for
    }
        private static List<CSVRecord> getCsvRecords () throws IOException {
            File targetfile = new File(fliePath);
            int SampleDataRow = 0;
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(targetfile))) {
                List<CSVRecord> records;

                try (CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().withQuote('"').parse(bufferedReader)) {
                    records = parser.getRecords();


                }
                return records;
            }
        }



    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}//class

