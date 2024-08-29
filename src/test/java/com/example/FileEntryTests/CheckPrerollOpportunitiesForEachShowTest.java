package com.example.FileEntryTests;

import com.example.InputData;
import com.example.JsonProcessor;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.files.fileEntryName;
import static constants.shows.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CheckPrerollOpportunitiesForEachShowTest {

    @Test
    public void CheckPrerollOpportunitiesForShows()
    {
        CheckPrerollOpportunities();
    }

    private void CheckPrerollOpportunities() {

        Map<String, Integer> expected = new HashMap<>();
        expected.put(StuffYouShouldKnow, 40);
        expected.put(WhoTrolledAmber, 40);
        expected.put(CrimeJunkie, 30);
        expected.put(TheJoeRoganExperience, 10);

        JsonProcessor processor = new JsonProcessor();
        List<InputData> dataList = processor.loadData(fileEntryName);

        assertNotNull(processor.loadData(fileEntryName));
        assertEquals(expected, processor.prerollOpportunities(dataList));
    }
}
