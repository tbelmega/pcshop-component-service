package de.oncoding.pcshop.componentservice.model

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class CpuCooler(
        @Id
        val id: String,
        @ElementCollection
        val supportedCpuSockets: Set<CpuSocket>
): PcComponent