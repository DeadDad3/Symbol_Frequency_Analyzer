package com.example.symbol_frequency_analyzer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class FrequencyController {

    @PostMapping("/calculate-frequency")
    public OutputData calculateFrequency(@RequestBody InputData inputData) {
        String inputString = inputData.getInputString();

        Map<Character, Integer> frequencyMap = inputString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));

        frequencyMap = frequencyMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        OutputData outputData = new OutputData(frequencyMap); // Передаем frequencyMap в конструктор OutputData
        return outputData;
    }
}
