package com.db.ToDoCrud.DTO;

import lombok.Builder;

@Builder
public record RequestTaskDto(String titulo, Boolean concluida) {
}
