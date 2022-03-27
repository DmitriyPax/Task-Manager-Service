package com.test.taskmanagerservice.repository

import com.test.taskmanagerservice.entity.Tag
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * This interface derives interface for CRUD operations on Tag entity
 */
@Repository
interface TagRepository : CrudRepository<Tag, UUID> {
}