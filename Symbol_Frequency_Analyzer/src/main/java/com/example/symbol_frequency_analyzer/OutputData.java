package com.example.symbol_frequency_analyzer;

import java.util.Map;

public class OutputData {
    private Map<Character, Integer> frequencyMap;

    public OutputData(Map<Character, Integer> frequencyMap) {
        this.frequencyMap = frequencyMap;
    }

    public Map<Character, Integer> getFrequencyMap() {
        return frequencyMap;
    }

    public void setFrequencyMap(Map<Character, Integer> frequencyMap) {
        this.frequencyMap = frequencyMap;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "frequencyMap=" + frequencyMap +
                '}';
    }
}