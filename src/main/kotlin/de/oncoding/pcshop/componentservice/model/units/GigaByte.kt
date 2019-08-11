package de.oncoding.pcshop.componentservice.model.units

import javax.persistence.AttributeConverter
import javax.persistence.Converter


data class GigaByte (val gigaByte: Int)


@Converter(autoApply = true)
class GigaByteConverter: AttributeConverter<GigaByte, Int> {
    override fun convertToDatabaseColumn(attribute: GigaByte) = attribute.gigaByte
    override fun convertToEntityAttribute(dbData: Int) = GigaByte(dbData)
}