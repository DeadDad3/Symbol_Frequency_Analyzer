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

    @Test
    public void testCalculateFrequency_differentCharacters() throws Exception {
        String inputString = "abcde";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put('a', 1);
        expectedFrequencyMap.put('b', 1);
        expectedFrequencyMap.put('c', 1);
        expectedFrequencyMap.put('d', 1);
        expectedFrequencyMap.put('e', 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inputString\":\"" + inputString + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequencyMap").value(expectedFrequencyMap));
    }

    @Test
    public void testCalculateFrequency_caseInsensitive() throws Exception {
        String inputString = "AaBbCc";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put('a', 2);
        expectedFrequencyMap.put('b', 2);
        expectedFrequencyMap.put('c', 2);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inputString\":\"" + inputString + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequencyMap").value(expectedFrequencyMap));
    }

    @Test
    public void testCalculateFrequency_multipleOccurrences() throws Exception {
        String inputString = "aaabbbccc";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put('a', 3);
        expectedFrequencyMap.put('b', 3);
        expectedFrequencyMap.put('c', 3);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inputString\":\"" + inputString + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequencyMap").value(expectedFrequencyMap));
    }

    @Test
    public void testCalculateFrequency_specialCharacters() throws Exception {
        String inputString = "!@#$%^&*()";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put('!', 1);
        expectedFrequencyMap.put('@', 1);
        expectedFrequencyMap.put('#', 1);
        expectedFrequencyMap.put('$', 1);
        expectedFrequencyMap.put('%', 1);
        expectedFrequencyMap.put('^', 1);
        expectedFrequencyMap.put('&', 1);
        expectedFrequencyMap.put('*', 1);
        expectedFrequencyMap.put('(', 1);
        expectedFrequencyMap.put(')', 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inputString\":\"" + inputString + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequencyMap").value(expectedFrequencyMap));
    }

    @Test
    public void testCalculateFrequency_whitespaceCharacters() throws Exception {
        String inputString = "Hello World";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put('H', 1);
        expectedFrequencyMap.put('e', 1);
        expectedFrequencyMap.put('l', 3);
        expectedFrequencyMap.put('o', 2);
        expectedFrequencyMap.put(' ', 1);
        expectedFrequencyMap.put('W', 1);
        expectedFrequencyMap.put('r', 1);
        expectedFrequencyMap.put('d', 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inputString\":\"" + inputString + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequencyMap").value(expectedFrequencyMap));
    }

}
