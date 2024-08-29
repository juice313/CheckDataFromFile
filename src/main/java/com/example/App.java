package com.example;

import java.util.List;

import static DateHelper.UnixTimeConverter.printUnixDate;

public class App {

    public static void main(String[] args) {
        JsonProcessor processor = new JsonProcessor();
        List<InputData> dataList = processor.loadData("downloads.txt");

        //Added the System.out.printlines here but the tests can be run all together from test folder as well
        System.out.println("1. Most popular show in San Francisco:");
        String regionSanFrancisco = "San Francisco";
        processor.mostPopularShowInRegion(regionSanFrancisco, dataList);

        System.out.println("\n2. Most popular device:");
        processor.mostPopularDevice(dataList);

        System.out.println("\n3. Preroll opportunities:");
        processor.prerollOpportunities(dataList);

        System.out.println("\n4. Weekly shows are:");
        processor.getWeeklyShows(dataList);

    }
}
