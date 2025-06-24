package com.db.ToDoCrud.Service.impl;

import com.db.ToDoCrud.DTO.RequestTaskDto;
import com.db.ToDoCrud.DTO.ResponseTaskDto;
import com.db.ToDoCrud.Exception.NotFoundException;
import com.db.ToDoCrud.Repository.TaskRepository;
import com.db.ToDoCrud.Models.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.db.ToDoCrud.Service.TaskService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    final TaskRepository taskRepository;

    @Override
    public List<ResponseTaskDto> getTasks() {
        return taskRepository.findAll().stream()
                .map(Task::toDto)
                .toList();
    }

    @Override
    public ResponseTaskDto createTask(RequestTaskDto taskDto) {
        Task newTask = Task.builder()
                .titulo(taskDto.titulo())
                .concluida(taskDto.concluida()).build();

        return taskRepository.save(newTask).toDto();
    }

    @Override
    public ResponseTaskDto updateTask(Long id, RequestTaskDto taskDto) {
        Optional<Task> taskToUp = taskRepository.findById(id);

        if(taskToUp.isEmpty()){
            throw new NotFoundException("Task não encontrada.");
        }

        Task updatedTask = Task.builder()
                .id(id).titulo(taskDto.titulo())
                .concluida(taskDto.concluida()).build();

        return taskRepository.save(updatedTask).toDto();
    }

    @Override
    public void deleteTask(Long id) {
        Optional<Task> taskToDel = taskRepository.findById(id);

        if(taskToDel.isEmpty()){
            throw new NotFoundException("Task não encontrada.");
        }

        taskRepository.deleteById(id);
        log.info("Task com id {} deletada com sucesso.", id);

    }
}
