package de.oncoding.pcshop.componentservice.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Cpu(
        @Id
        val id: String
): PcComponent