package com.example.symbol_frequency_analyzer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class SymbolFrequencyAnalyzerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculateFrequency() throws Exception {
        String inputString = "aabbbcc";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put('b', 3);
        expectedFrequencyMap.put('a', 2);
        expectedFrequencyMap.put('c', 2);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inputString\":\"" + inputString + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequencyMap").value(expectedFrequencyMap));
    }

    @Test
    public void testCalculateFrequency_emptyString() throws Exception {
        String inputString = "";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inputString\":\"" + inputString + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequencyMap").value(expectedFrequencyMap));
    }

    // Добавить дополнительные тесты для проверки других сценариев

}
