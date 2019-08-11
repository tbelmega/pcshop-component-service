package de.oncoding.pcshop.componentservice.model.units

import javax.persistence.AttributeConverter
import javax.persistence.Converter


data class CFM (val cfm: Float)

@Converter(autoApply = true)
class CFMConverter: AttributeConverter<CFM?, Float?> {
    override fun convertToDatabaseColumn(attribute: CFM?) = attribute?.cfm
    override fun convertToEntityAttribute(dbData: Float?) = dbData?.let { CFM(it) }
}