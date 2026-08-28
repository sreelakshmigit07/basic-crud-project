package org.example.fintechproject.service;

import org.example.fintechproject.dto.TaskDTO;
import org.example.fintechproject.entity.Task;
import org.example.fintechproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    // with methods: createTask, getAllTasks, getTaskById, updateTask, deleteTask

    public void createTask(TaskDTO taskDTO){
        try{
            Task taskEntity = new Task();
            taskEntity.setTaskName(taskDTO.getTaskName());
            taskEntity.setTaskType(taskDTO.getTaskType());
            taskRepository.save(taskEntity);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());// will change to custom exception later
        }

    }

    public List<TaskDTO> getAllTasks(){
        try {
            List<TaskDTO> taskListResponse = new ArrayList<>();
            List<Task> taskList = taskRepository.findAll();
            for(Task task: taskList){
                TaskDTO dto = new TaskDTO();
                dto.setTaskName(task.getTaskName());
                dto.setTaskType(task.getTaskType());
                taskListResponse.add(dto);
            }
            return taskListResponse;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());// will change to custom exception later
        }
    }

    public TaskDTO getTaskById(Long id){

            Task taskEntity = taskRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Task not found with Id: "+id)
            );
        TaskDTO dto = new TaskDTO();
                dto.setTaskType(taskEntity.getTaskType());
                dto.setTaskName(taskEntity.getTaskName());

          return dto;
    }

    public TaskDTO updateTask(Long id, TaskDTO dto) {
            Task taskEntity = taskRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Task not found with id:"+ id));

            taskEntity.setTaskName(dto.getTaskName());
            taskEntity.setTaskType(dto.getTaskType());
            Task updated = taskRepository.save(taskEntity);

            TaskDTO responseDto = new TaskDTO();
            responseDto.setTaskName(updated.getTaskName());
            responseDto.setTaskType(updated.getTaskType());
            return  responseDto;
    }

    public  void deleteTask(Long id){
        if(!taskRepository.existsById(id)){
            throw new RuntimeException("Task not found with Id: "+id);
        }
        taskRepository.deleteById(id);
    }
}
