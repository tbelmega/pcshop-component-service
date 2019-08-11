package de.oncoding.pcshop.componentservice.model.pccomponents

import de.oncoding.pcshop.componentservice.model.units.CFM
import de.oncoding.pcshop.componentservice.model.units.CpuSocket
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class CpuCooler(
        @Id
        val id: String,
        override val model: String,
        override val manufacturer: String,
        @ElementCollection
        val supportedCpuSockets: Set<CpuSocket>,
        val airFlowInCFM: CFM
): PcComponent