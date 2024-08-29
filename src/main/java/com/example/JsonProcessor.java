package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static DateHelper.UnixTimeConverter.convertUnixToDayTime;

public class JsonProcessor {

    public List<InputData> loadData(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<InputData> dataList = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                InputData inputData = objectMapper.readValue(line, InputData.class);
                dataList.add(inputData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public Map<String, String> mostPopularShowInRegion(String region, List<InputData> dataList) {
        Map<String, Integer> showDownloadCounts = new HashMap<>();
        Map<String, String> mostPopularShowInRegionAndNumberOfDownloads = new HashMap<>();

        for (InputData inputData : dataList) {
            if (region.equalsIgnoreCase(inputData.getCity())) {
                String showId = inputData.getDownloadIdentifier().getShowId();
                showDownloadCounts.put(showId, showDownloadCounts.getOrDefault(showId, 0) + 1);
            }
        }

        Map.Entry<String, Integer> mostPopularShow = showDownloadCounts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (mostPopularShow != null) {
            System.out.println("Most popular show is: " + mostPopularShow.getKey());
            System.out.println("Number of downloads is: " + mostPopularShow.getValue());
        }

        mostPopularShowInRegionAndNumberOfDownloads.put(mostPopularShow.getKey(), mostPopularShow.getValue().toString());

        return mostPopularShowInRegionAndNumberOfDownloads;
    }

    public Map<String, String> mostPopularDevice(List<InputData> dataList) {
        Map<String, Integer> deviceCounts = new HashMap<>();
        Map<String, String> mostPopularDeviceNameAndNumberOfDownloads = new HashMap<>();

        for (InputData inputData : dataList) {
            String deviceType = inputData.getDeviceType();
            deviceCounts.put(deviceType, deviceCounts.getOrDefault(deviceType, 0) + 1);
        }

        Map.Entry<String, Integer> mostPopularDevice = deviceCounts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (mostPopularDevice != null) {
            System.out.println("Most popular device is: " + mostPopularDevice.getKey());
            System.out.println("Number of downloads is: " + mostPopularDevice.getValue());
            mostPopularDeviceNameAndNumberOfDownloads.put(mostPopularDevice.getKey(),mostPopularDevice.getValue().toString());
        }
        return mostPopularDeviceNameAndNumberOfDownloads;
    }

    public Map<String, Integer> prerollOpportunities(List<InputData> dataList) {
        Map<String, Integer> prerollCounts = new HashMap<>();

        for (InputData inputData : dataList) {
            String showId = inputData.getDownloadIdentifier().getShowId();
            for (Opportunity opportunity : inputData.getOpportunities()) {
                if (opportunity.getPositionUrlSegments().get("aw_0_ais.adBreakIndex").contains("preroll")) {
                    prerollCounts.put(showId, prerollCounts.getOrDefault(showId, 0) + 1);
                }
            }
        }

        prerollCounts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Show Id: " + entry.getKey() + ", Preroll Opportunity Number: " + entry.getValue()));

        return prerollCounts;

    }

    public Map<String, String> getWeeklyShows(List<InputData> dataList) {
        Map<String, List<String>> podcastData = new HashMap<>();

        for (InputData data : dataList) {
            String showId = data.getDownloadIdentifier().getShowId();

            data.getOpportunities().forEach(opportunity -> {
                long originalEventTime = opportunity.getOriginalEventTime();
                String dayTime = convertUnixToDayTime(originalEventTime);

                podcastData.putIfAbsent(showId, new ArrayList<>());
                podcastData.get(showId).add(dayTime);
            });
        }

        Map<String, String> weeklyShows = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : podcastData.entrySet()) {
            String showId = entry.getKey();
            List<String> daysTimes = entry.getValue();

            Set<String> uniqueDaysTimes = new HashSet<>(daysTimes);

            if (uniqueDaysTimes.size() == 1) {
                weeklyShows.put(showId, uniqueDaysTimes.iterator().next());
            }
        }

        System.out.println("Weekly shows are:");
        for (Map.Entry<String, String> entry : weeklyShows.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        return weeklyShows;
    }
}
