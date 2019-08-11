package de.oncoding.pcshop.componentservice.model.pccomponents

import de.oncoding.pcshop.componentservice.model.units.Watts
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class PowerSupplyUnit(
        @Id
        val id: String,
        override val model: String,
        override val manufacturer: String,
        val wattage: Watts
): PcComponent