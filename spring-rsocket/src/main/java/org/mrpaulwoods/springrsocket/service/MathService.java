package org.mrpaulwoods.springrsocket.service;

import org.mrpaulwoods.springrsocket.dto.ChartResponseDto;
import org.mrpaulwoods.springrsocket.dto.ComputationRequestDto;
import org.mrpaulwoods.springrsocket.dto.ComputationResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MathService {

    // fire-and-forget
    public Mono<Void> print(Mono<ComputationRequestDto> requestDtoMono) {
        return requestDtoMono
                .doOnNext(System.out::println)
                .then();
    }

    // request-response
    public Mono<ComputationResponseDto> findSquare(Mono<ComputationRequestDto> requestDtoMono) {
        return requestDtoMono
                .map(ComputationRequestDto::getInput)
                .map(i -> new ComputationResponseDto(i, i * i));
    }

    // request-stream
    public Flux<ComputationResponseDto> tableStream(ComputationRequestDto dto) {
        return Flux.range(1, 10)
                .map(i -> new ComputationResponseDto(dto.getInput(), dto.getInput() * i));
    }

    // request-channel  // square + 1
    public Flux<ChartResponseDto> chartStream(Flux<ComputationRequestDto> requestDtoFlux) {
        return requestDtoFlux
                .map(ComputationRequestDto::getInput)
                .map(i -> new ChartResponseDto(i, (i * i) + 1));
    }

}
