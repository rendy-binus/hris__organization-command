package com.rendyezaputra.hris.organizationcommand.api.dto.response;

import com.rendyezaputra.hris.hriswebresources.dto.BaseResponse;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobFunctionResponse extends BaseResponse {
    private UUID id;

    @Builder
    public JobFunctionResponse(String message, UUID id) {
        super(message);
        this.id = id;
    }
}
