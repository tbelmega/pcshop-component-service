package de.oncoding.pcshop.componentservice.model.units

import javax.persistence.AttributeConverter
import javax.persistence.Converter


data class MegaBytesPerSecond (val megaBytesPerSecond: Int)


@Converter(autoApply = true)
class MegaBytesPerSecondConverter: AttributeConverter<MegaBytesPerSecond, Int> {
    override fun convertToDatabaseColumn(attribute: MegaBytesPerSecond) = attribute.megaBytesPerSecond
    override fun convertToEntityAttribute(dbData: Int) = MegaBytesPerSecond(dbData)
}