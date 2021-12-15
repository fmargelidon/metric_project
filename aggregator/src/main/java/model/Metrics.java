package model;

import java.time.LocalDateTime;

/** Represents a metric.
 * @author Francis Margelidon
 * @version 1.0
 */
public class Metrics {
    private String processName;
    private double pid;
    private double value;
    private LocalDateTime eventDate;
    private String hostname;
    private MetricType metricType;

    /** Sets the processus name.
     * @param processName A String containing the processus name
     */
    public void setProcessName(String processName) { this.processName = processName; }

    /** Sets the PID of processus
     * @param pid A double containing the pid
     */
    public void setPid(double pid) { this.pid = pid; }

    /** Sets value of the metrics
     * @param value A double containing the value of the metric
     */
    public void setValue(double value) { this.value = value; }

    /** Sets the event's date
     * @param eventDate A LocalDateTime containing the date when the metric was collected
     */
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    /** Sets the hostname
     * @param hostname A String containing the hostname
     */
    public void setHostname(String hostname) { this.hostname = hostname; }

    /** Sets the type of metric
     * @param metricType A MetricType containing the type of metric which was collected
     */
    public void setMetricType(MetricType metricType) { this.metricType = metricType; }

    /** Get the processus name
     * @return A string representing the processus name
     */
    public String getProcessName() { return processName; }

    /** Get the pid
     * @return A double representing the pid
     */
    public double getPid() { return pid; }

    /** Get the value
     * @return A double representing the value of the metrics
     */
    public double getValue() { return value; }

    /** Get the event's date
     * @return A LocalDateTime representing the date when metrics was collected
     */
    public LocalDateTime getEventDate() { return eventDate; }

    /** Get the hostname
     * @return A string representing the hostname
     */
    public String getHostname() { return hostname; }

    /** Get the Metric Type
     * @return A MetricType representing the type of metric collected
     */
    public MetricType getMetricType() { return metricType; }
}
