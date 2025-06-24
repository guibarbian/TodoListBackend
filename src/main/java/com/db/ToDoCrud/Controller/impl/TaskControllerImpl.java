package com.db.ToDoCrud.Controller.impl;

import com.db.ToDoCrud.Controller.TaskController;
import com.db.ToDoCrud.DTO.RequestTaskDto;
import com.db.ToDoCrud.DTO.ResponseTaskDto;
import com.db.ToDoCrud.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

    final TaskService taskService;

    @Override
    @GetMapping
    public ResponseEntity<List<ResponseTaskDto>> getTasks() {
        return ResponseEntity.status(200).body(taskService.getTasks());
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseTaskDto> createTask(@RequestBody RequestTaskDto taskDto) {
        return ResponseEntity.status(201).body(taskService.createTask(taskDto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTaskDto> updateTask(@PathVariable Long id,
                                                      @RequestBody RequestTaskDto taskDto) {
        return ResponseEntity.status(200).body(taskService.updateTask(id, taskDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(200).body("Task de id " + id + " deletada.");
    }
}
