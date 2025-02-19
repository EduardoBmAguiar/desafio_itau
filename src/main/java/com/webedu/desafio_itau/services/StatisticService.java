package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Statistic;
import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
public class StatisticService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Statistic getStatistic(Integer lastMinutes) {

        Statistic statistic = new Statistic();

        List<Transaction> list = transactionRepository.getTransactions();

        log.info("Getting a list of transitional in the last minute");
        lastMinutes = lastMinutes * 60;
        OffsetDateTime past = OffsetDateTime.now().minusSeconds(lastMinutes);
        List<Transaction> pastTransactions = list.stream().filter(x -> x.getDataHora().isAfter(past)).toList();

        log.info("Checking for any transitions in the last minute");
        if (pastTransactions.isEmpty()) {
            statistic.setMin(BigDecimal.ZERO);
            log.info("No transitions in the last minute: returning (0) for all statistics");
            return statistic;
        }
        log.info("List of transactions in the last minute success: {}", pastTransactions.toArray().length);

        log.info("Starting calculation of statistics");
        for (Transaction t : pastTransactions) {

            statistic.setCount(statistic.getCount() + 1);

            statistic.setSum(statistic.getSum().add(t.getValor()));

            if (t.getValor().compareTo(statistic.getMax()) > 0) statistic.setMax(t.getValor());

            if (t.getValor().compareTo(statistic.getMin()) < 0) statistic.setMin(t.getValor());

        }

        statistic.setAvg(statistic.getSum().divide(BigDecimal.valueOf(statistic.getCount()), 2, RoundingMode.HALF_UP));

        log.info("Statistics calculation successfully, returning statistics");
        return statistic;
    }
}
