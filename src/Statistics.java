import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    private int totalTraffic;
    public int totalError=0;
    public HashMap <String, Integer> userVisit = new HashMap<>();
    public int totalVisitQtyNotBot=0;
    public LocalDateTime minTime;
    public LocalDateTime maxTime;
    public LocalDateTime minTimeExclBot;
    public LocalDateTime maxTimeExclBot;
    private HashSet<LogEntry> listPages;
    public HashSet<String> existPages = new HashSet<>();
    public HashSet <String> nonExistPages = new HashSet<>();
    public HashMap<String, Integer> browserCount = new HashMap<>();


    public Statistics() {
        this.totalTraffic = 0;
        this.minTimeExclBot = null;
        this.maxTimeExclBot = null;
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
        if (!isBot(logEntry.userAgent)) {
            totalVisitQtyNotBot++;
            if (this.minTimeExclBot == null || logEntryTime.isBefore(minTimeExclBot)) {
                minTimeExclBot = logEntryTime;
            }
            if (this.maxTimeExclBot == null || logEntryTime.isAfter(maxTimeExclBot)) {
                maxTimeExclBot = logEntryTime;
            }
        }
        if (this.minTime == null || logEntryTime.isBefore(minTime)) {
            minTime = logEntryTime;
        }
        if (this.maxTime == null || logEntryTime.isAfter(maxTime)) {
            maxTime = logEntryTime;
        }
        if (logEntry.responseCode == 200) {
            existPages.add(logEntry.path);
        }
        if (logEntry.responseCode >= 400 && logEntry.responseCode < 600){
            totalError++;
        }

        if (!isBot(logEntry.userAgent)){
            userVisit.put(logEntry.ipAddress, userVisit.getOrDefault(logEntry.ipAddress,0)+1);
        }
        String sys = new UserAgent(logEntry.userAgent).toString();

        if (browserCount.containsKey(sys)) {
            int count = browserCount.get(sys);
            browserCount.put(sys, count +1);
        } else {
            browserCount.put(sys, 1);
        }
        if (logEntry.responseCode == 404) {
            nonExistPages.add(logEntry.path);
        }

        listPages.add(logEntry);
    }

    public HashMap<String, Double> getBrowserCount () {
        HashMap<String, Double> sysStatistic = new HashMap<>();
        int totalSys = 0;
            for (int count : browserCount.values()) {
                totalSys += count;
            }
                for (String sys : browserCount.keySet()) {
                    int count = browserCount.get(sys);
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
    public Double getAvgTotalVisitPerHour (){
        Duration duration = Duration.between(minTimeExclBot, maxTimeExclBot);
        double hour = duration.toHours();
        return totalVisitQtyNotBot/hour;
    }

    public Double getAvgErrorPerHour (){
        Duration duration = Duration.between(minTime, maxTime);
        double hour = duration.toHours();
        return totalError/hour;
    }

    public Integer getAvgVisitUniqUser (){
        return totalVisitQtyNotBot/userVisit.size();
    }

    public boolean isBot (String userAgent) {
        return userAgent.contains("bot");
    }
}