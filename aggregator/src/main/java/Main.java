import database.PostgreConnection;
import model.Data;
import parser.ReadJSON;

import java.io.File;

/** Main class
 * @author Francis Margelidon
 * @version 1.0
 */
public class Main {

    public static void main(String args[]) {

        // Check number of parameter. Need to have at least one
        if (args.length > 0 ) {

            File path = new File(args[0]);
            // Check if path in parameter exist and is a directory
            if (path.exists() && path.isDirectory()) {

                // Display folder to scan
                System.out.println("Folder to scan : "+path);

                // Get File list in folder
                String[] jsonList = path.list();

                // Loop on file in directory
                for (String file : jsonList) {

                    // Display file to parse
                    System.out.println("File "+path+file+" will be parsed");

                    // Initialize a new ReadJSON with the file found
                    ReadJSON myJSON = new ReadJSON(args[0]+file);
                    // Get all data from the file
                    Data data = myJSON.getData();

                    // Display file to parse
                    System.out.println("End of parsing file "+path+file);

                    // Display information
                    System.out.println("Try to connect to database...");

                    // Connect to postgreSQL database
                    PostgreConnection postgreConnection = PostgreConnection.getInstance();

                    // Display information
                    System.out.println("Insert data in database");

                    // Insert all data in database
                    postgreConnection.insertNodeLoad(data.getNodeLoad());
                    postgreConnection.insertMetrics(data.getRssMemoryProcess());
                    postgreConnection.insertMetrics(data.getElapsedTimeProcess());

                    // Close postgreSQL connection
                    postgreConnection.close();

                    // Display information
                    System.out.println("Close connection");
                }
            } else {
                System.err.println("Folder "+path+" does not exists");
            }

        } else {
            System.err.println("Wrong parameter number.");
            System.err.println("You need to have at least one parameter to indicate the folder where JSON file are stored.");
            System.err.println("Usage : java -jar aggregator.jar <PATH_TO_FOLDER>");
        }

        // Display information
        System.out.println("End of aggregator daemon");
    }
}
