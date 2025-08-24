package org.mrpaulwoods.springrsocket.dto;

import lombok.NonNull;

public record ChartResponseDto(int input, int output) {

    @Override
    @SuppressWarnings("unused")
    public int input() {
        return input;
    }

    @Override
    @SuppressWarnings("unused")
    public int output() {
        return output;
    }

    @Override
    @NonNull
    public String toString() {
        String graphFormat = getFormat(this.output);
        return String.format(graphFormat, this.input, "X");
    }

    private String getFormat(int value) {
        return "%3s|%" + value + "s";
    }

}
