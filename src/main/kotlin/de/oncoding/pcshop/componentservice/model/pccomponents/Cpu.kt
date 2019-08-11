package de.oncoding.pcshop.componentservice.model.pccomponents

import de.oncoding.pcshop.componentservice.model.units.CpuSocket
import de.oncoding.pcshop.componentservice.model.units.MegaHerz
import de.oncoding.pcshop.componentservice.model.units.Watts
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Cpu(
        @Id
        val id: String,
        override val model: String,
        override val manufacturer: String,
        val cpuSocket: CpuSocket,
        val clockRateInMHz: MegaHerz,
        val numberOfCores: Int,
        val wattage: Watts
): PcComponent