package com.test.taskmanagerservice.repository

import com.test.taskmanagerservice.entity.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * This interface derives interface for CRUD operations on Task entity
 */
@Repository
interface TaskRepository : CrudRepository<Task, UUID> {
    /**
     * Method for search all tasks by tag
     *
     * @param id Id is tag UUID
     */
    fun findAllByTagId(id: UUID): Iterable<Task>
}