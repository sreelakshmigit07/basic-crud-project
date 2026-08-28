package org.example.fintechproject.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.fintechproject.dto.TaskDTO;
import org.example.fintechproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    // POST /api/tasks
    @PostMapping()
    public ResponseEntity<String> createTask(@Valid @RequestBody TaskDTO requestDto){
            taskService.createTask(requestDto);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Task created successfully!");

    }

    // GET /api/tasks
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
            return ResponseEntity.ok().body(taskService.getAllTasks());

    }

    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){

            return ResponseEntity.ok().body(taskService.getTaskById(id));


    }

    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id ,@Valid @RequestBody TaskDTO dto){
            return ResponseEntity.ok().body(taskService.updateTask(id,dto));

    }


    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
    }

}
