package com.test.taskmanagerservice.dto

import com.test.taskmanagerservice.entity.Tag
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class TaskDTO (
    var id: UUID?,
    @field:Valid
    @field:NotBlank(message = "Пустое название!")
    @field:Size(min = 3, max = 255, message = "Длина названия!")
    var name: String,
    @field:Valid
    @field:NotBlank(message = "Пустое описание!")
    @field:Size(min = 3, max = 255, message = "Длина описания!")
    var definition: String,
    @field:Valid
    @field:NotBlank(message = "Пустая дата!")
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var date: LocalDate,
    var data: ByteArray? = null,
    @field:Valid
    @field:NotBlank(message = "Пустой тэг!")
    var tag: Tag,
)