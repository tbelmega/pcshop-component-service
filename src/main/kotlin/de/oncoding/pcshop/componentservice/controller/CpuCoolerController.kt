package de.oncoding.pcshop.componentservice.controller

import de.oncoding.pcshop.componentservice.CpuCoolerRepository
import de.oncoding.pcshop.componentservice.model.pccomponents.CpuCooler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CpuCoolerController(
        val cpuCoolerRepository: CpuCoolerRepository
) {

    @GetMapping(value = ["/api/v1/cpucoolers"])
    fun getCpuCoolers(
            @RequestParam(required = false) socket: String?
    ): List<CpuCooler> {
        return cpuCoolerRepository.findAll().filter {
            socket == null || it.supportedCpuSockets.map {
                it.name.toLowerCase()
            }.contains(socket)
        }
    }

//    faulty implementation
//    @GetMapping(value = ["/api/v1/cpucoolers"])
//    fun getCpuCoolers(
//            @RequestParam socket: String
//    ): List<CpuCooler> {
//        return cpuCoolerRepository.findAll().filter {
//            it.supportedCpuSockets.map {
//                it.name.toLowerCase()
//            }.contains(socket)
//        }
//    }

//    @GetMapping(value = ["/api/v1/cpucoolers"])
//    fun getCpuCoolers(): List<CpuCooler> {
//        return cpuCoolerRepository.findAll()
//    }

}
