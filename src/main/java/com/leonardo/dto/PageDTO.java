package com.leonardo.dto;

import java.util.List;

public record PageDTO<T>(List<T> courses, long totalElements, int totalPages) {
}
