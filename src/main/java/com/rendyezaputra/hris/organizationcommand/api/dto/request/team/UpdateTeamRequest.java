package com.rendyezaputra.hris.organizationcommand.api.dto.request.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamRequest {
    @NotNull
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Min(0) @Max(32767)
    private short level;

    @NotBlank
    @Size(min = 6, max = 6)
    private String color;
}
