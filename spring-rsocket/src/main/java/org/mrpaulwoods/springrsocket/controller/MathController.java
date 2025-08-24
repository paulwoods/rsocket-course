package org.mrpaulwoods.springrsocket.controller;

import org.mrpaulwoods.springrsocket.dto.ChartResponseDto;
import org.mrpaulwoods.springrsocket.dto.ComputationRequestDto;
import org.mrpaulwoods.springrsocket.dto.ComputationResponseDto;
import org.mrpaulwoods.springrsocket.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class MathController {

    @Autowired
    private MathService service;

    @MessageMapping("math.service.print")
    public Mono<Void> print(Mono<ComputationRequestDto> requestDtoMono) {
        return service.print(requestDtoMono);
    }

    @MessageMapping("math.service.square")
    public Mono<ComputationResponseDto> findSquare(Mono<ComputationRequestDto> requestDtoMono) {
        return service.findSquare(requestDtoMono);
    }

    @MessageMapping("math.service.table")
    public Flux<ComputationResponseDto> tableStream(Mono<ComputationRequestDto> requestDtoMono) {
        return requestDtoMono.flatMapMany(service::tableStream);
    }

    @MessageMapping("math.service.chart")
    public Flux<ChartResponseDto> chartStream(Flux<ComputationRequestDto> requestDtoMono) {
        return service.chartStream(requestDtoMono);
    }

}
