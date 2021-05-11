package tqs.ua.AirQuality.service;

import tqs.ua.AirQuality.model.AirQuality;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private int numberOfRequests;
    private int numberOfHits;
    private int numberOfMisses;
    private long timeToLive;

    private Map<String, AirQuality> requestsData;

    private Map<String, Long> requestsTime;

    public Cache(long ttl) {
        this.requestsData = new HashMap<>();
        this.requestsTime = new HashMap<>();
        this.timeToLive = ttl;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }

    public int getNumberOfMisses() {
        return numberOfMisses;
    }

    private boolean hasExpired(String name) {
        Long expireTime = this.requestsTime.get(name);
        return System.currentTimeMillis() > expireTime;
    }

    public AirQuality getRequest(String name) {
        this.numberOfRequests++;
        AirQuality request = null;

        if (!this.requestsTime.containsKey(name)) {
            this.numberOfMisses++;
        } else if (hasExpired(name)) {
            this.requestsData.remove(name);
            this.requestsTime.remove(name);
            this.numberOfMisses++;
        } else {
            this.numberOfHits++;
            request = this.requestsData.get(name);
        }

        return request;
    }

    public AirQuality getRequest(String lat, String lon, String day) {
        this.numberOfRequests++;
        AirQuality request = null;
        String key = lat+lon+day;

        if (!this.requestsTime.containsKey(key)) {
            this.numberOfMisses++;
        } else if ( hasExpired(key) ) {
            this.requestsData.remove(key);
            this.requestsTime.remove(key);
            this.numberOfMisses++;
        } else {
            this.numberOfHits++;
            request = this.requestsData.get(key);
        }

        return request;
    }

    public void saveRequest(String key, AirQuality result) {
        this.requestsData.put(key, result);
        this.requestsTime.put(key, System.currentTimeMillis() + this.timeToLive * 1000);
    }

    public void saveRequest(String lat, String lng, String from, AirQuality result) {
        String key = lat+lng+from;
        this.requestsData.put(key, result);
        this.requestsTime.put(key, System.currentTimeMillis() + this.timeToLive * 1000);
    }

}