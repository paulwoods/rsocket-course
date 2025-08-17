package org.mrpaulwoods.rsocket.dto;

public class ChartResponseDto {

    private final int input;
    private final int output;

    public ChartResponseDto(int input, int output) {
        this.input = input;
        this.output = output;
    }

    @SuppressWarnings("unused")
    public int getInput() {
        return input;
    }

    @SuppressWarnings("unused")
    public int getOutput() {
        return output;
    }

    @Override
    public String toString() {
        String graphFormat = getFormat(this.output);
        return String.format(graphFormat, this.input, "X");
    }

    private String getFormat(int value) {
        return "%3s|%" + value + "s";
    }

}
