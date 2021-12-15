package parser;

import model.Data;
import model.MetricType;
import model.Metrics;
import model.NodeLoad;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Class to parse a JSON file which contains metrics
 * @author Francis Margelidon
 * @version 1.0
 */
public class ParseJSONMetrics {
    private Data data;

    /** Function to parse a JSON file to Data class
     * @param fileObject A JSONObject to parse
     * @return A Data object contains all metrics obtains after parsing the JSON file
     */
    public Data convertJSONtoMetrics(JSONObject fileObject) {
        // Retrieve data information
        NodeLoad nodeLoad = new NodeLoad();
        data = new Data();

        // Parse first part contains metadata information
        JSONObject metadataObject = (JSONObject) fileObject.get("metadata");
        String node = (String) metadataObject.get("node");
        nodeLoad.setHostname(node);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        LocalDateTime start = LocalDateTime.parse((String) metadataObject.get("time"), formatter);
        System.out.println("Test : "+start.toString());
        nodeLoad.setEventDate(start);


        // Retrieve second part for metrics information
        JSONObject metricsObject = (JSONObject) fileObject.get("metrics");
        nodeLoad.setValue((Double) ((JSONObject) metricsObject.get("node_load1")).get("value"));
        data.setNodeLoad(nodeLoad);

        // Browse Process CPU seconds array
        JSONArray cpuSeconds = (JSONArray) metricsObject.get("process_cpu_seconds");
        for (int i = 0; i < cpuSeconds.size(); i++) {
            Metrics metrics = new Metrics();
            metrics.setHostname(node);
            metrics.setEventDate(start);
            metrics.setMetricType(MetricType.ElapsedTime);
            metrics.setProcessName((String) ((JSONObject) cpuSeconds.get(i)).get("name"));
            metrics.setPid((Long) ((JSONObject) cpuSeconds.get(i)).get("pid"));
            metrics.setValue((Long) ((JSONObject) cpuSeconds.get(i)).get("value"));
            data.addElapsedTime(metrics);
        }

        // Browse Process RSS Memory array
        JSONArray elapsedTime = (JSONArray) metricsObject.get("process_rss_bytes");
        for (int i = 0; i < cpuSeconds.size(); i++) {
            Metrics metrics = new Metrics();
            metrics.setHostname(node);
            metrics.setEventDate(start);
            metrics.setMetricType(MetricType.RSSMemory);
            metrics.setProcessName((String) ((JSONObject) cpuSeconds.get(i)).get("name"));
            metrics.setPid((Long) ((JSONObject) cpuSeconds.get(i)).get("pid"));
            metrics.setValue((Long) ((JSONObject) cpuSeconds.get(i)).get("value"));
            data.addRssMemoryProcess(metrics);
        }

        return data;
    }

}
