package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.entities.Statistic;
import com.webedu.desafio_itau.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estatistica")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Statistic> getStatistic(Statistic obj) {

            obj = statisticService.getStatistic();

            return ResponseEntity.ok().body(obj);
    }




}
