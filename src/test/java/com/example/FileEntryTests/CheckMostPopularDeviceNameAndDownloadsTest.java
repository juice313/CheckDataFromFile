package com.example.FileEntryTests;

import com.example.InputData;
import com.example.JsonProcessor;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.devices.MobilesAndTablets;
import static constants.files.fileEntryName;
import static org.junit.Assert.*;

public class CheckMostPopularDeviceNameAndDownloadsTest {
    @Test
    public void CheckPopularDeviceNameAndNumberOfDownloadsTest()
    {
        CheckMostPopularDevicesAndNumberOfDownloads();
    }

    private void CheckMostPopularDevicesAndNumberOfDownloads() {

        final String expectedNumberOfDownloads = "60";

        Map<String, String> expectedPopularDeviceAndNumberOfDownloads = new HashMap<>();
        expectedPopularDeviceAndNumberOfDownloads.put(MobilesAndTablets, expectedNumberOfDownloads);

        JsonProcessor processor = new JsonProcessor();
        List<InputData> dataList = processor.loadData(fileEntryName);

        assertNotNull(processor.loadData(fileEntryName));
        assertEquals(expectedPopularDeviceAndNumberOfDownloads, processor.mostPopularDevice(dataList));
    }
}