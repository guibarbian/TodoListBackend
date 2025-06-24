package com.db.ToDoCrud.Service;

import com.db.ToDoCrud.DTO.RequestTaskDto;
import com.db.ToDoCrud.DTO.ResponseTaskDto;

import java.util.List;

public interface TaskService {

    List<ResponseTaskDto> getTasks();

    ResponseTaskDto createTask(RequestTaskDto taskDto);

    ResponseTaskDto updateTask(Long id, RequestTaskDto taskDto);

    void deleteTask(Long id);
}
