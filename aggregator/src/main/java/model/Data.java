package model;

import java.util.ArrayList;

/** Class to store all type of metrics
 * @author Francis Margelidon
 * @version 1.0
 */
public class Data {
    private NodeLoad nodeLoad;
    private ArrayList<Metrics> rssMemoryProcess;
    private ArrayList<Metrics> elapsedTimeProcess;

    /** Constructor to initiliaze two arrays of mertics
     */
    public Data() {
        rssMemoryProcess = new ArrayList<Metrics>();
        elapsedTimeProcess = new ArrayList<Metrics>();
    }

    /** Sets the node load data type
     * @param nodeLoad A NodeLoad class
     */
    public void setNodeLoad(NodeLoad nodeLoad) { this.nodeLoad = nodeLoad; }

    /** Add a metrics data into RSS Memory Process array
     * @param metrics A Metrics class
     */
    public void addRssMemoryProcess(Metrics metrics) { this.rssMemoryProcess.add(metrics); }

    /** Add a metrics data into Elapsed Time array
     * @param metrics A Metrics class
     */
    public void addElapsedTime(Metrics metrics) { this.elapsedTimeProcess.add(metrics); }

    /** Get the NodeLoad object
     * @return A NodeLoad object
     */
    public NodeLoad getNodeLoad() { return nodeLoad; }

    /** Get all information for RSS Memory Process array
     * @return A array of all mertcis for RSS Memory Process
     */
    public ArrayList<Metrics> getRssMemoryProcess() { return rssMemoryProcess; }

    /** Get all information for Elapsed Time Process array
     * @return A array of all mertcis for Elapsed Time Process
     */
    public ArrayList<Metrics> getElapsedTimeProcess() { return elapsedTimeProcess; }
}
