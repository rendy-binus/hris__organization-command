package com.rendyezaputra.hris.organizationcommand.api.dto.request.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamRequest {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(min = 6, max = 6)
    private String color;
}
