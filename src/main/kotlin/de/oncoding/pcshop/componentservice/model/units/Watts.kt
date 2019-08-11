package de.oncoding.pcshop.componentservice.model.units

import javax.persistence.AttributeConverter
import javax.persistence.Converter

data class Watts (val watts: Int)

@Converter(autoApply = true)
class WattsConverter: AttributeConverter<Watts, Int> {
    override fun convertToDatabaseColumn(attribute: Watts) = attribute.watts
    override fun convertToEntityAttribute(dbData: Int) = Watts(dbData)
}
