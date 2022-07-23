package com.ibetar.taskstodo.controller;

import com.ibetar.taskstodo.persistence.entity.Task;
import com.ibetar.taskstodo.persistence.entity.TaskStatus;
import com.ibetar.taskstodo.service.TaskService;
import com.ibetar.taskstodo.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }

    @GetMapping
    public List<Task> getTasks(){
        return this.taskService.getTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus taskStatus){
        return this.taskService.findAllByTaskStatus(taskStatus);
    }

    @PatchMapping("/isfinished/{id}")
    public ResponseEntity<Void> taskIsFinished(@PathVariable("id") Long id){
        this.taskService.updateTaskStatusAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable("id") Long id){
        this.taskService.deleteTaskByiId(id);
        return ResponseEntity.noContent().build();
    }
}
