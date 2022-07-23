package com.ibetar.taskstodo.service;

import com.ibetar.taskstodo.exceptions.TaskToDoExceptions;
import com.ibetar.taskstodo.mapper.TaskInDTOToTask;
import com.ibetar.taskstodo.persistence.entity.Task;
import com.ibetar.taskstodo.persistence.entity.TaskStatus;
import com.ibetar.taskstodo.persistence.repository.TaskRepository;
import com.ibetar.taskstodo.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO){
        Task task = mapper.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> getTasks(){
        return this.taskRepository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus){
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskStatusAsFinished(Long id){
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new TaskToDoExceptions("Task ID not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markTaskAsFinished(id);
    }

    public void deleteTaskByiId (Long id){
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new TaskToDoExceptions("Task ID not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }
}
