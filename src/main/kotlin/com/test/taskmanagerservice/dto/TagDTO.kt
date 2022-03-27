package com.test.taskmanagerservice.dto

import org.springframework.validation.annotation.Validated
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
class TagDTO (
    var id: UUID?,
    @field:Valid
    @field:NotBlank(message = "Пустое название тэга")
    @field:Size(min = 3, max = 255, message = "Длина названия тэга не соответствует размеру от 3 до 255")
    var name: String
)