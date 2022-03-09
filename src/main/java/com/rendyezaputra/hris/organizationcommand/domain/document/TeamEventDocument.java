package com.rendyezaputra.hris.organizationcommand.domain.document;

import com.rendyezaputra.hris.hriswebresources.cqrs.document.EventDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
@Document(collection = "TeamEventStore")
public class TeamEventDocument extends EventDocument {
}
