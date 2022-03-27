package com.test.taskmanagerservice.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import java.util.*
import javax.persistence.*

/**
 * This class describes Task entity
 *
 * @property id Id is task UUID
 * @property name Name is task name
 * @property definition Definition is task definition
 * @property date Date is task assignment date
 * @property data Data is attachment file
 * @property tag Tag is foreing key tag UUID
 */
@Entity
@Table(name =  "t_tasks")
class Task(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID?,
        var name: String,
        var definition: String,
        var date: LocalDate,
        var data: ByteArray? = null,
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "tag_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var tag: Tag,
) {
        constructor(id: UUID?, name: String, definition: String, date: LocalDate, tag: Tag) :
                this(id, name, definition, date, null, tag)
}