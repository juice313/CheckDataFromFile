package com.example.FileEntryTests;

import com.example.InputData;
import com.example.JsonProcessor;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.files.fileEntryName;
import static constants.shows.CrimeJunkie;
import static constants.shows.WhoTrolledAmber;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CheckWeeklyShowsTest {

    @Test
    public void CheckWeeklyShows()
    {
        CheckAndDisplayWeeklyShows();
    }

    private void CheckAndDisplayWeeklyShows()
    {
        Map<String, String> expectedWeeklyShow = new HashMap<>();
        expectedWeeklyShow.put(CrimeJunkie, "Wed 22:00");
        expectedWeeklyShow.put(WhoTrolledAmber, "Mon 20:00");

        JsonProcessor processor = new JsonProcessor();
        List<InputData> dataList = processor.loadData(fileEntryName);

        assertNotNull(processor.loadData(fileEntryName));
        assertEquals(expectedWeeklyShow, processor.getWeeklyShows(dataList));
    }
}
