package de.oncoding.pcshop.componentservice.model.pccomponents

import de.oncoding.pcshop.componentservice.model.units.CpuSocket
import de.oncoding.pcshop.componentservice.model.units.MegaHerz
import de.oncoding.pcshop.componentservice.model.units.MotherBoardFormat
import de.oncoding.pcshop.componentservice.model.units.RAMType
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class MotherBoard(
        @Id
        val id: String,
        override val model: String,
        override val manufacturer: String,
        val cpuSocket: CpuSocket,
        val format: MotherBoardFormat,
        val chipset: String,
        val ramType: RAMType,
        val maxRAMClockRateInMHz: MegaHerz
): PcComponent
