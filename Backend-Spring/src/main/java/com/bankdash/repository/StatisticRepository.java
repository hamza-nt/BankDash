package com.bankdash.repository;

import com.bankdash.entity.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticRepository extends MongoRepository<Statistic, String> {

}
