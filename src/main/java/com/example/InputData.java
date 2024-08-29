package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputData {
    private DownloadIdentifier downloadIdentifier;
    private List<Opportunity> opportunities;
    private String deviceType;
    private String city;

    public InputData() {
    }

    public DownloadIdentifier getDownloadIdentifier() {
        return downloadIdentifier;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getCity() {
        return city;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class DownloadIdentifier {
    private String showId;

    public String getShowId() {
        return showId;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Opportunity {
    private long originalEventTime;
    private Map<String, List<String>> positionUrlSegments;

    public long getOriginalEventTime() {
        return originalEventTime;
    }

    public Map<String, List<String>> getPositionUrlSegments() {
        return positionUrlSegments;
    }
}