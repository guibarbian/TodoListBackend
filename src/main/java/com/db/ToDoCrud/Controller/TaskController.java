package com.db.ToDoCrud.Controller;

import com.db.ToDoCrud.DTO.RequestTaskDto;
import com.db.ToDoCrud.DTO.ResponseTaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskController {

    ResponseEntity<List<ResponseTaskDto>> getTasks();

    ResponseEntity<ResponseTaskDto> createTask(RequestTaskDto taskDto);

    ResponseEntity<ResponseTaskDto> updateTask(Long id, RequestTaskDto taskDto);

    ResponseEntity<String> deleteTask(Long id);
}
