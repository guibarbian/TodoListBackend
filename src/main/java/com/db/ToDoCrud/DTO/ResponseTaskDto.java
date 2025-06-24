package com.db.ToDoCrud.DTO;

import lombok.Builder;

@Builder
public record ResponseTaskDto(Long id, String titulo, Boolean concluida) {
}
