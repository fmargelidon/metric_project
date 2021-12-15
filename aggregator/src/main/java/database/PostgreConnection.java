package database;

import model.Metrics;
import model.NodeLoad;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/** Class to connect to postegreSQL database
 * and to add metrics and node_load data
 * @author Francis Margelidon
 * @version 1.0
 */
public class PostgreConnection {
    private static PostgreConnection instance;
    private Connection conn;
    private PreparedStatement pstmt;

    /** Private constructor to do a single connection to database
     */
    private PostgreConnection() {
        // Properties to connect to postgres database with
        // User: postgres and password : postgres
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");

        String url = "jdbc:postgresql://db/postgres";
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /** Static method getInstance to initialize db connection
     */
    public static PostgreConnection getInstance() {
        if (instance == null) {
            instance = new PostgreConnection();
        }
        return instance;
    }

    /** Method to insert data NodeLoad in node_load table
     * @param nodeLoad A NodeLoad to be insert in node_load table
     */
    public void insertNodeLoad(NodeLoad nodeLoad) {
        // Build SQL to insert data in node_load table
        String SQLinsert = "INSERT INTO node_load (hostname,event_date,value) " + "VALUES(?,?,?)";

        int rowsAffected = 0;
        try {
            // Initialize value with data
            pstmt = conn.prepareStatement(SQLinsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nodeLoad.getHostname());
            pstmt.setTimestamp(2, Timestamp.valueOf(nodeLoad.getEventDate()));
            pstmt.setDouble(3, nodeLoad.getValue());

            // Execute statement for node_load table
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /** Method to insert data Metrics in metrics table
     * @param metrics A array of Metrics to be insert in metrics table
     */
    public void insertMetrics(ArrayList<Metrics> metrics) {
        // Build SQL to insert data in metrics table
        String SQLinsert = "INSERT INTO metrics (process_name,pid,value,event_date,hostname,id_metric_type) "
                + "VALUES(?,?,?,?,?,?)";

        int rowsAffected = 0;
        try {
            // Initialize value with data
            pstmt = conn.prepareStatement(SQLinsert, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < metrics.size(); i++) {
                pstmt.setString(1, metrics.get(i).getProcessName());
                pstmt.setDouble(2, metrics.get(i).getPid());
                pstmt.setDouble(3, metrics.get(i).getValue());
                pstmt.setTimestamp(4, Timestamp.valueOf(metrics.get(i).getEventDate()));
                pstmt.setString(5, metrics.get(i).getHostname());
                pstmt.setInt(6, metrics.get(i).getMetricType().ordinal());

                // Execute statement for metrics
                rowsAffected = pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /** Method to close db connection
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
