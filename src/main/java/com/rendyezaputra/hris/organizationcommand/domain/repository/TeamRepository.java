package com.rendyezaputra.hris.organizationcommand.domain.repository;

import com.rendyezaputra.hris.organizationcommand.domain.document.TeamEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends MongoRepository<TeamEventDocument, String> {
    List<TeamEventDocument> findByAggregateIdentifier(String aggregateIdentifier);
}
