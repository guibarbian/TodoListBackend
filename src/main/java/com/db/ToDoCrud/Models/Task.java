package com.db.ToDoCrud.Models;

import com.db.ToDoCrud.DTO.ResponseTaskDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "concluida", nullable = false)
    private Boolean concluida;

    public ResponseTaskDto toDto(){
        return ResponseTaskDto.builder()
                .id(this.id)
                .titulo(this.titulo)
                .concluida(this.concluida).build();
    }
}
