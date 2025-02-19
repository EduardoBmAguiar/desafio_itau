package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.annotation.TrackExecutionTime;
import com.webedu.desafio_itau.entities.Statistic;
import com.webedu.desafio_itau.services.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/estatistica")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @TrackExecutionTime
    @Operation(description = "Está requizição faz o calculo das Estatisticas: count, sum, avg, min, max.", summary = "Realiza o cálculo das estátisticas", method = "GET")
    @ApiResponse(responseCode = "200", description = "Calculo Feito, e retornado")
    @GetMapping
    public ResponseEntity<Statistic> getStatistic(@RequestParam(value = "lastMinutes", defaultValue = "1") Integer lastMinutes) {
            log.info("Trying Get statistic");
            Statistic obj = statisticService.getStatistic(lastMinutes);
            log.info("Statistics obtained");
            return ResponseEntity.ok().body(obj);
    }
}
