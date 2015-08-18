package net.sf.openrocket.flightcomputer;

/**
 * FlightComputer
 *
 * @author Chris Andre
 */

public abstract class FlightComputer {
    private StringBuilder log;
    public FlightComputer() {
        log = new StringBuilder("");
    }
    public void log(String msg) {
        log.append(msg);
        log.append('\n');
    }
    public String getLogCopy() {
        return log.toString();
    }
    public void reset() {
        log = new StringBuilder("");
    }
}
