package de.oncoding.pcshop.componentservice.model.units

import javax.persistence.AttributeConverter
import javax.persistence.Converter


data class MegaHerz (val megaHerz: Int)

@Converter(autoApply = true)
class MegaHerzConverter: AttributeConverter<MegaHerz, Int> {
    override fun convertToDatabaseColumn(attribute: MegaHerz) = attribute.megaHerz
    override fun convertToEntityAttribute(dbData: Int) = MegaHerz(dbData)
}
