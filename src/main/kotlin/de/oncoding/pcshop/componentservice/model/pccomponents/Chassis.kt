package de.oncoding.pcshop.componentservice.model.pccomponents

import de.oncoding.pcshop.componentservice.model.units.ChassisFormat
import de.oncoding.pcshop.componentservice.model.units.MotherBoardFormat
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Chassis(
        @Id
        val id: String,
        override val model: String,
        override val manufacturer: String,
        val motherBoardFormat: MotherBoardFormat,
        val size: ChassisFormat
): PcComponent