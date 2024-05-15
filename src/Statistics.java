import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    private int totalTraffic;
    public LocalDateTime minTime;
    public LocalDateTime maxTime;
    private HashSet<LogEntry> listPages;
    public HashSet<String> existPages = new HashSet<>();
    public HashMap<String, Integer> typeSysCount = new HashMap<>();


    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.listPages = new HashSet<>();
    }
    public HashSet<String> getExistPages() {
        return existPages;
    }
    public void addEntry(LogEntry logEntry) {
        totalTraffic += logEntry.getDataSize();
        LocalDateTime logEntryTime = logEntry.getDateTime();
        if (this.minTime == null || logEntryTime.isBefore(minTime)) {
            minTime = logEntryTime;
        }
        if (this.maxTime == null || logEntryTime.isAfter(maxTime)) {
            maxTime = logEntryTime;
        }
        if (logEntry.responseCode == 200) {
            existPages.add(logEntry.path);
        }

        String sys = new UserAgent(logEntry.userAgent).toString();

        if (typeSysCount.containsKey(sys)) {
            int count = typeSysCount.get(sys);
            typeSysCount.put(sys, count +1);
        } else {
            typeSysCount.put(sys, 1);
        }


        listPages.add(logEntry);
    }
    public HashMap<String, Double> getTypeSysCount () {
        HashMap<String, Double> sysStatistic = new HashMap<>();
        int totalSys = 0;
        for (int count : typeSysCount.values()) {
            totalSys += count;
        }
        for (String sys : typeSysCount.keySet()) {
            int count = typeSysCount.get(sys);
            double fraction = (double) count / totalSys;
            sysStatistic.put(sys, fraction);
        }
        return sysStatistic;
    }
    public int getTrafficRate() {
        if (listPages.isEmpty()) {
            return 0;
        }
        Duration duration = Duration.between(minTime, maxTime);
        double hour = duration.toHours();
        return (int) (totalTraffic / hour);
    }
}