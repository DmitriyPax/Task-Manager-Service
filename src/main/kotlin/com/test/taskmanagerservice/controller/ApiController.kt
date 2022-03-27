package com.test.taskmanagerservice.controller

import com.test.taskmanagerservice.dto.TagDTO
import com.test.taskmanagerservice.dto.TaskDTO
import com.test.taskmanagerservice.entity.Tag
import com.test.taskmanagerservice.entity.Task
import com.test.taskmanagerservice.service.TagService
import com.test.taskmanagerservice.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.validation.Valid

/**
 * This class contains HTTP request handlers
 */
@RestController
class ApiController {

    @Autowired
    lateinit var tagService: TagService

    @Autowired
    lateinit var taskService: TaskService

    /**
     * Return all tasks
     */
    @GetMapping("/tasks")
    fun findAllTasks() = taskService.findAll()


    /**
     * Return all tasks by tag
     *
     * @param id is UUID for tag
     */
    @Cacheable("tasks")
    @GetMapping("/tag/{id}")
    fun findAllTasksById(@PathVariable id: UUID) = taskService.findAllByTagId(id)

    /**
     * Save or update tag
     *
     * @param tagDTO is new tag
     * @return query result
     */
    @PostMapping("/tag")
    fun saveTag(@RequestBody @Valid tagDTO: TagDTO): Tag {
        return if (tagDTO.id == null)
            tagService.create(tagDTO)
        else
            tagService.update(tagDTO)
    }

    /**
     * Save or update new task
     *
     * @param taskDTO is new task
     * @return query result
     */
    @PostMapping("/task")
    fun saveTask(@RequestBody taskDTO: TaskDTO): Task {
        return if (taskDTO.id == null)
            taskService.create(taskDTO)
        else
            taskService.update(taskDTO)
    }

    /**
     * Delete task by id
     *
     * @param id is task UUID
     * @return query result
     */
    @DeleteMapping("/task/{id}")
    fun deleteTask(@PathVariable id: UUID) = taskService.deleteById(id)

    /**
     * Delete tag by id and all tasks by this tag
     *
     * @param id is tag UUID
     * @return query result
     */
    @DeleteMapping("/tag/{id}")
    fun deleteTag(@PathVariable id: UUID) = tagService.deleteById(id)

    /**
     * Save file for task
     *
     * @param id is task UUID
     * @param file is saved file
     * @return query result
     */
    @PostMapping("/task/{id}/upload")
    fun uploadFile(@PathVariable id: UUID, @RequestParam file: MultipartFile) = taskService.update(id, file)
}