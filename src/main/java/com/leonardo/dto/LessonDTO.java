package com.leonardo.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonDTO(
                Long id,
                @NotNull @NotBlank @Length(min = 5, max = 100) String name,
                @NotNull @NotBlank @Length(min = 5, max = 15) String youtubeUrl) {
}
