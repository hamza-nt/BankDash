package com.bankdash.repository;

import com.bankdash.entity.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransferRepository extends MongoRepository<Transfer, String> {

}
