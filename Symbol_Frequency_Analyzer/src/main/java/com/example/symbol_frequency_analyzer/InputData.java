package com.example.symbol_frequency_analyzer;

public class InputData {
    private String inputString;

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "inputString='" + inputString + '\'' +
                '}';
    }
}