package com.leonardo.dto;

import java.util.List;

public record PageDTO<T>(List<T> records, long totalElements, int totalPages) {
}
