import database.PostgreConnection;
import model.Data;
import model.MetricType;
import model.Metrics;
import model.NodeLoad;
import org.junit.Test;
import parser.ReadJSON;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** Class to test aggregetor app
 * @author Francis Margelidon
 * @version 1.0
 */
public class AggregatorTest {
    /** Test to parse a JSON example file
     */
    @Test
    public final void testParseJSON() {
        ReadJSON myJSON = new ReadJSON("src\\test\\resources\\20211210113506.json");
        myJSON.getData();
    }

    /** Test to insert NodeLoad in database
     */
    @Test
    public final void testInsertNodeLoadDatabase() {
        NodeLoad nodeLoad = new NodeLoad();
        nodeLoad.setHostname("test");
        nodeLoad.setEventDate(LocalDateTime.now());
        nodeLoad.setValue(0.65);

        /*PostgreConnection postgreConnection = PostgreConnection.getInstance();
        postgreConnection.insertNodeLoad(nodeLoad);
        postgreConnection.close();*/
    }

    /** Test to insert Metric in database
     */
    @Test
    public final void testInsertMetricDatabase() {
        ArrayList<Metrics> rssMemoryMetric = new ArrayList<Metrics>();
        Metrics metrics = new Metrics();
        metrics.setHostname("test");
        metrics.setEventDate(LocalDateTime.now());
        metrics.setValue(4563);
        metrics.setPid(454364);
        metrics.setProcessName("myprocess");
        metrics.setMetricType(MetricType.RSSMemory);
        rssMemoryMetric.add(metrics);

        /*PostgreConnection postgreConnection = PostgreConnection.getInstance();
        postgreConnection.insertMetrics(rssMemoryMetric);
        postgreConnection.close();*/
    }

    /** Test to insert NodeLoad and metrics in database
     */
    @Test
    public final void testInsertDataDatabase() {
        NodeLoad nodeLoad = new NodeLoad();
        nodeLoad.setHostname("test");
        nodeLoad.setEventDate(LocalDateTime.now());
        nodeLoad.setValue(0.65);

        Metrics rssMemoryMetric = new Metrics();
        rssMemoryMetric.setHostname("test");
        rssMemoryMetric.setEventDate(LocalDateTime.now());
        rssMemoryMetric.setValue(4563);
        rssMemoryMetric.setPid(454364);
        rssMemoryMetric.setProcessName("myprocess");
        rssMemoryMetric.setMetricType(MetricType.RSSMemory);

        Metrics elapsedTimeMetric = new Metrics();
        elapsedTimeMetric.setHostname("test2");
        elapsedTimeMetric.setEventDate(LocalDateTime.now());
        elapsedTimeMetric.setValue(435);
        elapsedTimeMetric.setPid(454364);
        elapsedTimeMetric.setProcessName("myprocess");
        elapsedTimeMetric.setMetricType(MetricType.ElapsedTime);

        Data data = new Data();
        data.setNodeLoad(nodeLoad);
        data.addRssMemoryProcess(rssMemoryMetric);
        data.addRssMemoryProcess(elapsedTimeMetric);

        /*PostgreConnection postgreConnection = PostgreConnection.getInstance();
        postgreConnection.insertNodeLoad(data.getNodeLoad());
        postgreConnection.insertMetrics(data.getElapsedTimeProcess());
        postgreConnection.insertMetrics(data.getRssMemoryProcess());
        postgreConnection.close();*/
    }

}
