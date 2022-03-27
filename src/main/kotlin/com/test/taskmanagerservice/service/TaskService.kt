package com.test.taskmanagerservice.service

import com.test.taskmanagerservice.dto.TaskDTO
import com.test.taskmanagerservice.entity.Task
import com.test.taskmanagerservice.exceptionHandler.MyException
import com.test.taskmanagerservice.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class TaskService {
    @Autowired
    lateinit var taskRepository: TaskRepository

    fun create(taskDTO: TaskDTO): Task {
        val task = Task(
            null,
            taskDTO.name,
            taskDTO.definition,
            taskDTO.date,
            taskDTO.tag
        )
        return taskRepository.save(task)
    }

    fun update(taskDTO: TaskDTO): Task {
        val task = taskRepository.findById(taskDTO.id!!).orElseThrow { MyException("Задача не найдена!") }
        task.id = taskDTO.id
        task.name = taskDTO.name
        task.definition = taskDTO.definition
        task.date = taskDTO.date
        task.data = taskDTO.data
        task.tag = taskDTO.tag
        return taskRepository.save(task)
    }

    fun update(id: UUID, file: MultipartFile): Task {
        var task = taskRepository.findById(id).orElseThrow { MyException("Задача не найдена!") }
        task.data = file.bytes
        return taskRepository.save(task)
    }

    fun deleteById(id: UUID) {
        return taskRepository.deleteById(id)
    }

    fun findAll(): Iterable<Task>{
        return taskRepository.findAll()
    }

    fun findAllByTagId(id: UUID): Iterable<Task>{
        return taskRepository.findAllByTagId(id)
    }
}