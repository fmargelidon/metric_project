package parser;

import model.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Class to read a JSON file
 * @author Francis Margelidon
 * @version 1.0
 */
public class ReadJSON {
    private String filename;

    /** Constructor to initiliaze ReadJSON class with the file to parse
     * @param filename A String containing the file to parse
     */
    public ReadJSON(String filename) {
        this.filename = filename;
    }

    /** Methode getData to read the JSON file to return
     * all information in Data element
     * @return A Data contains all metrics obtains after parsing the JSON file
     */
    public Data getData() {
        Data data = null;

        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        // Open file to read
        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            JSONObject metricsObject = (JSONObject)  jsonParser.parse(reader);

            // Parse JSON file with ParseJSONMetrics class
            ParseJSONMetrics myJSONconverter = new ParseJSONMetrics();
            data = myJSONconverter.convertJSONtoMetrics(metricsObject);

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        return data;
    }
}
