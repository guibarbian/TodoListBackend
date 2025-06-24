package com.db.ToDoCrud.Controller;

import com.db.ToDoCrud.DTO.RequestTaskDto;
import com.db.ToDoCrud.Models.Task;
import com.db.ToDoCrud.Repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setup(){
        taskRepository.deleteAll();
    }

    String baseUrl = "/api/v1/tasks";
    Task task = new Task(null, "Estudar Java", false);
    RequestTaskDto taskDto = new RequestTaskDto("Estudar Java", false);
    RequestTaskDto taskDto2 = new RequestTaskDto("Estudar Javaa", true);

    @Test
    public void testGetTasks() throws Exception{
        taskRepository.save(task);

        mockMvc.perform(get(baseUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Estudar Java"));
    }

    @Test
    public void testPostTask() throws Exception{
        mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.titulo").value("Estudar Java"))
                .andExpect(jsonPath("$.concluida").value(false));
    }

    @Test
    public void testUpdateTask() throws Exception{
        Task savedTask = taskRepository.save(task);

        mockMvc.perform(put(baseUrl + "/" + savedTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.titulo").value("Estudar Javaa"))
                .andExpect(jsonPath("$.concluida").value(true));
    }

    @Test
    public void testDeleteTask() throws Exception{
        Task savedTask = taskRepository.save(task);

        mockMvc.perform(delete(baseUrl + "/" + savedTask.getId()))
                .andExpect(status().isOk());
    }
}
