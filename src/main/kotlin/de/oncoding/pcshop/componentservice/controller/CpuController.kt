package de.oncoding.pcshop.componentservice.controller

import de.oncoding.pcshop.componentservice.CpuRepository
import de.oncoding.pcshop.componentservice.model.pccomponents.Cpu
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CpuController(
        val cpuRepository: CpuRepository
) {

    @GetMapping(value = ["/api/v1/cpus"])
    fun getCpus(
            @RequestParam format: String
    ): List<Cpu> {
        return cpuRepository.findAll().filter {
            it.cpuSocket.name.equals(format, ignoreCase = true)
        }
    }

}
