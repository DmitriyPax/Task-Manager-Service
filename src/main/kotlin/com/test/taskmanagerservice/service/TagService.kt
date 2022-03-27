package com.test.taskmanagerservice.service

import com.test.taskmanagerservice.dto.TagDTO
import com.test.taskmanagerservice.entity.Tag
import com.test.taskmanagerservice.exceptionHandler.MyException
import com.test.taskmanagerservice.repository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagService {
    @Autowired
    lateinit var tagRepository: TagRepository

    fun create(tagDTO: TagDTO): Tag {
        val tag = Tag(tagDTO.name)
        return tagRepository.save(tag)
    }

    fun update(tagDTO: TagDTO): Tag {
        var tag = tagRepository.findById(tagDTO.id!!).orElseThrow { MyException("Тэг не найден") }
        tag.name = tagDTO.name
        return tagRepository.save(tag)
    }

    fun deleteById(id: UUID) {
        return tagRepository.deleteById(id)
    }
}