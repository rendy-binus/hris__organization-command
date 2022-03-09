package com.rendyezaputra.hris.organizationcommand.api.dto.request.jobfunction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobFunctionRequest {
    @NotBlank
    @Size(max = 50)
    private String name;

    @Min(0) @Max(32767)
    private short level;

    @NotBlank
    @Size(min = 6, max = 6)
    private String color;
}
