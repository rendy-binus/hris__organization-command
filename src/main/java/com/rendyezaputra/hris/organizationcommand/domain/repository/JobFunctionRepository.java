package com.rendyezaputra.hris.organizationcommand.domain.repository;

import com.rendyezaputra.hris.organizationcommand.domain.document.JobFunctionEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobFunctionRepository extends MongoRepository<JobFunctionEventDocument, String> {
    List<JobFunctionEventDocument> findByAggregateIdentifier(String aggregateIdentifier);
}
