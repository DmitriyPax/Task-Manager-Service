package com.test.taskmanagerservice.entity

import java.util.*
import javax.persistence.*

/**
 * This class describes Tag entity
 *
 * @property id Id is UUID for tag
 * @property name Name is name for tag
 * @property tasks Tasks is list of all tasks for tag
 */
@Entity
@Table(name = "t_tags")
class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID?,
    var name: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "tag")
    private var tasks: MutableList<Task>? = mutableListOf<Task>()
) {
    constructor(name: String) : this(null, name)
}