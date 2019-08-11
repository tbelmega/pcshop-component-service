package de.oncoding.pcshop.componentservice.model.pccomponents

import de.oncoding.pcshop.componentservice.model.units.GigaByte
import de.oncoding.pcshop.componentservice.model.units.MegaBytesPerSecond
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class HardDiskDrive(
        @Id
        val id: String,
        override val model: String,
        override val manufacturer: String,
        val sizeInGB: GigaByte,
        val transferRateInMBperS: MegaBytesPerSecond
): PcComponent