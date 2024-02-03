package com.leonardo.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
                @JsonProperty("_id") Long id,
                @NotNull @NotBlank @Length(min = 5, max = 100) String name,
                @NotNull @NotBlank @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category) {
}
