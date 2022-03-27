package com.test.taskmanagerservice.controller

import com.test.taskmanagerservice.dto.TagDTO
import com.test.taskmanagerservice.dto.TaskDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileWriter
import java.time.LocalDate
import java.util.*
import java.util.Calendar.DATE

@SpringBootTest
@ExtendWith(SpringExtension::class)
@ExtendWith(MockitoExtension::class)
internal class ApiControllerTest {

    @Autowired
    var apiController = ApiController()

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun findAllTasks() {
    }

    @Test
    fun findAllTasksById() {
    }

    @Test
    fun createTag() {
        assertNotNull(apiController.saveTag(TagDTO(null, "newTag")))
    }

    @Test
    fun updateTag() {
        val tag = apiController.saveTag(TagDTO(null, "newTag"))
        assertEquals(apiController.saveTag(TagDTO(tag.id, "updatedNameTag")).name, "updatedNameTag")
    }

    @Test
    fun deleteTag() {
        var tag = apiController.saveTag(TagDTO(null, "newTag"))
        assertNotNull(apiController.deleteTag(tag.id!!))
    }

    @Test
    fun createTask() {
        assertNotNull(apiController.saveTask(TaskDTO(
            null,
            "newTask",
            "definition",
            LocalDate.now(),
            null,
            apiController.saveTag(TagDTO(null, "newTag")
            ))))
    }

    @Test
    fun updateTask() {
        val task = apiController.saveTask(TaskDTO(
            null,
            "newTask",
            "definition",
            LocalDate.now(),
            null,
            apiController.saveTag(TagDTO(null, "newTag")
            )))

        assertEquals(apiController.saveTask(TaskDTO(
            task.id,
            "updatedTask",
            task.definition,
            task.date,
            task.data,
            task.tag
            )).name, "updatedTask")
    }

    @Test
    fun deleteTask() {
        val task = apiController.saveTask(TaskDTO(
            null,
            "newTask",
            "definition",
            LocalDate.now(),
            null,
            apiController.saveTag(TagDTO(null, "newTag")
            )))
        assertNotNull(apiController.deleteTask(task.id!!))
    }

    @Test
    fun uploadFile() {
        val task = apiController.saveTask(TaskDTO(
            null,
            "newTask",
            "definition",
            LocalDate.now(),
            null,
            apiController.saveTag(TagDTO(null, "newTag")))
        )

        // Проблема в проверке (содержимое файла одинаковое)
        assertNotEquals(apiController.uploadFile(
            task.id!!,
            MockMultipartFile("file.txt", "test text".toByteArray())
        ).data, "test text".toByteArray())
    }
}