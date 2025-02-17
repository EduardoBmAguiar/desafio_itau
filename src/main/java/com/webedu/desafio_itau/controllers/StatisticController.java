package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.entities.Statistic;
import com.webedu.desafio_itau.services.StatisticService;
import com.webedu.desafio_itau.services.exceptions.StatistcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estatistica")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Statistic> getStatistic(Statistic obj) {
        try {
            obj = statisticService.getStatistic();

            return ResponseEntity.ok().body(obj);
        } catch (ArithmeticException e) {
            throw new StatistcException(e.getMessage() + "Error when obtaining statistical data");
        }
    }




}
