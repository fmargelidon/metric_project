package model;

import java.time.LocalDateTime;

/** Represents a node load.
 * @author Francis Margelidon
 * @version 1.0
 */
public class NodeLoad {
    private String hostname;
    private LocalDateTime eventDate;
    private Double value;

    /** Sets the event's date
     * @param eventDate A LocalDateTime containing the date when the metric was collected
     */
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    /** Sets the hostname
     * @param hostname A String containing the hostname
     */
    public void setHostname(String hostname) { this.hostname = hostname; }

    /** Sets value of the metrics
     * @param value A double containing the value of the metric
     */
    public void setValue(Double value) { this.value = value; }

    /** Get the event's date
     * @return A LocalDateTime representing the date when metrics was collected
     */
    public LocalDateTime getEventDate() { return eventDate; }

    /** Get the hostname
     * @return A string representing the hostname
     */
    public String getHostname() { return hostname; }

    /** Get the value
     * @return A double representing the value of the metrics
     */
    public Double getValue() { return value; }
}
