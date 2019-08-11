package de.oncoding.pcshop.componentservice.model.pccomponents

import de.oncoding.pcshop.componentservice.model.units.GigaByte
import de.oncoding.pcshop.componentservice.model.units.MegaHerz
import de.oncoding.pcshop.componentservice.model.units.Watts
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class GraphicsCard(
        @Id
        val id: String,
        override val model: String,
        override val manufacturer: String,
        val vramInGB: GigaByte,
        val coreClockRateInMHz: MegaHerz,
        val wattage: Watts
): PcComponent