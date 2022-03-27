package com.test.taskmanagerservice.dto

import org.springframework.http.HttpStatus

class ErrorDTO (
    var typeError: String?,
    var error: String?,
    var status: HttpStatus
)