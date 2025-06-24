package com.db.ToDoCrud.Unitario;

import com.db.ToDoCrud.DTO.RequestTaskDto;
import com.db.ToDoCrud.DTO.ResponseTaskDto;
import com.db.ToDoCrud.Models.Task;
import com.db.ToDoCrud.Repository.TaskRepository;
import com.db.ToDoCrud.Service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    Task task1 = new Task(1L, "Estudar Java", false);
    Task task2 = new Task(2L, "Estudar PHP", false);

    RequestTaskDto requestTaskDto1 = new RequestTaskDto("Estudar Python", false);

    @Test
    public void testGetTasks(){

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        List<ResponseTaskDto> response = taskService.getTasks();

        assertEquals(2, response.size());
        assertEquals("Estudar Java", response.get(0).titulo());
        assertEquals("Estudar PHP", response.get(1).titulo());
        verify(taskRepository).findAll();
    }

    @Test
    public void testCreateTask(){
        Task newTask = new Task(null, requestTaskDto1.titulo(), requestTaskDto1.concluida());
        Task savedTask = new Task(3L, newTask.getTitulo(), newTask.getConcluida());

        when(taskRepository.save(newTask)).thenReturn(savedTask);

        ResponseTaskDto responseTaskDto = taskService.createTask(requestTaskDto1);

        assertEquals(3L, responseTaskDto.id());
        assertEquals("Estudar Python", responseTaskDto.titulo());
        assertFalse(responseTaskDto.concluida());
        verify(taskRepository).save(newTask);
    }

    @Test
    public void testCreateTaskWithNullValues(){
        RequestTaskDto requestTaskDto = new RequestTaskDto(null, null);

        Exception ex = assertThrows(NullPointerException.class, () -> {
            taskService.createTask(requestTaskDto);
        });

        String message = ex.getMessage();
    }

    @Test
    public void testUpdateTask(){
        RequestTaskDto updateDto = new RequestTaskDto("Estudar Java", true);
        Task updateTask = new Task(1L, updateDto.titulo(), updateDto.concluida());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        when(taskRepository.save(updateTask)).thenReturn(updateTask);

        ResponseTaskDto responseTaskDto = taskService.updateTask(1L, updateDto);

        assertEquals(1L, responseTaskDto.id());
        assertEquals("Estudar Java", responseTaskDto.titulo());
        assertTrue(responseTaskDto.concluida());
        verify(taskRepository).findById(1L);
        verify(taskRepository).save(updateTask);
    }

    @Test
    public void testDeleteTask(){
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        taskService.deleteTask(1L);

        verify(taskRepository).findById(1L);
        verify(taskRepository).deleteById(1L);
    }
}
