package com.example.FileEntryTests;

import com.example.InputData;
import com.example.JsonProcessor;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.files.fileEntryName;
import static constants.regions.regionSanFrancisco;
import static constants.shows.WhoTrolledAmber;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CheckMostPopularShowInSanFranciscoAndDownloadsTests {
    @Test
    public void CheckMostPopularShowInSanFranciscoAndNumberOfDownloadsTest()
    {
        CheckMostPopularShowAndNumberOfDownloads();
    }

    private void CheckMostPopularShowAndNumberOfDownloads()
    {
        final String expectedNumberOfDownloads = "24";

        Map<String, String> expectedMostPopularShowAndNumberOfDownloads = new HashMap<>();
        expectedMostPopularShowAndNumberOfDownloads.put(WhoTrolledAmber, expectedNumberOfDownloads);

        JsonProcessor processor = new JsonProcessor();
        List<InputData> dataList = processor.loadData(fileEntryName);

        assertNotNull(processor.loadData(fileEntryName));
        assertEquals(expectedMostPopularShowAndNumberOfDownloads, processor.mostPopularShowInRegion(regionSanFrancisco, dataList));
    }

}
