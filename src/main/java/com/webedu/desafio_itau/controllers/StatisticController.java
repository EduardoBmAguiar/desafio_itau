package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.entities.Statistic;
import com.webedu.desafio_itau.services.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/estatistica")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Statistic> getStatistic() {
            log.info("Trying Get statistic");
            Statistic obj = statisticService.getStatistic();
            log.info("Statistics obtained");
            return ResponseEntity.ok().body(obj);
    }
}
