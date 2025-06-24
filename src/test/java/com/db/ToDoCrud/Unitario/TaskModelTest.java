package com.db.ToDoCrud.Unitario;

import com.db.ToDoCrud.DTO.ResponseTaskDto;
import com.db.ToDoCrud.Models.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskModelTest {

    @Test
    public void testToDto(){
        Task task = new Task(1L, "Estudar Java", false);

        ResponseTaskDto dto = task.toDto();

        assertEquals(ResponseTaskDto.class, dto.getClass());
        assertEquals(1L, dto.id());
        assertEquals("Estudar Java", dto.titulo());
        assertFalse(dto.concluida());
    }
}
