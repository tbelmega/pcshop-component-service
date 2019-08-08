package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.PcComponent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ComponentController(
        val mainboardRepository: MainboardRepository,
        val cpuRepository: CpuRepository
) {

    @GetMapping
    fun getComponents(
            @RequestParam categories: Set<String>
    ): List<PcComponent> {
        return if (categories.contains("mainboard")) mainboardRepository.findAll()
        else cpuRepository.findAll()
    }
}