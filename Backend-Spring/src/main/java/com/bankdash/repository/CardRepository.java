package com.bankdash.repository;

import com.bankdash.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {

}
