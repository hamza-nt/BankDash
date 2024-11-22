package com.bankdash.repository;

import com.bankdash.entity.BalanceHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BalanceHistoryRepository extends MongoRepository<BalanceHistory, String> {

}
