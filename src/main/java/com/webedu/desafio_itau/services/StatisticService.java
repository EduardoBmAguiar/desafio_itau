package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Statistic;
import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Statistic getStatistic() {

        Statistic statistic = new Statistic();

        List<Transaction> list = transactionRepository.getTransactions();

        OffsetDateTime past = OffsetDateTime.now().minusSeconds(60);
        List<Transaction> pastTransactions = list.stream().filter(x -> x.getDataHora().isAfter(past)).toList();
        for (Transaction t : pastTransactions) {

            statistic.setCount(statistic.getCount() + 1);

            statistic.setSum(statistic.getSum().add(t.getValor()));

            if (t.getValor().compareTo(statistic.getMax()) > 0) statistic.setMax(t.getValor());

            if (t.getValor().compareTo(statistic.getMin()) < 0) statistic.setMin(t.getValor());

        }

        statistic.setAvg(statistic.getSum().divide(BigDecimal.valueOf(statistic.getCount()), 2, RoundingMode.HALF_UP));

        return statistic;
    }
}
