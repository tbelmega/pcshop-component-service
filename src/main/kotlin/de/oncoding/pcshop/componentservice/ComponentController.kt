package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.pccomponents.PcComponent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ComponentController(
        val mainBoardRepository: MainBoardRepository,
        val chassisRepository: ChassisRepository,
        val cpuRepository: CpuRepository,
        val cpuCoolerRepository: CpuCoolerRepository,
        val graphicsCardRepository: GraphicsCardRepository,
        val hardDiskDriveRepository: HardDiskDriveRepository,
        val powerSupplyRepository: PowerSupplyRepository,
        val randomAccessMemoryRepository: RandomAccessMemoryRepository,
        val solidStateDriveRepository: SolidStateDriveRepository
) {

    @GetMapping
    fun getComponents(
            @RequestParam categories: Set<String>
    ): List<PcComponent> {
        return categories.flatMap { repositoryForComponentCategory(it).findAll() }
    }

    private fun repositoryForComponentCategory(componentCategory: String): JpaRepository<out PcComponent, String> {
        return when (componentCategory) {
            "chassis"-> chassisRepository
            "cpu"-> cpuRepository
            "cpuCooler"-> cpuCoolerRepository
            "graphicsCard"-> graphicsCardRepository
            "hdd"-> hardDiskDriveRepository
            "mainboard"-> mainBoardRepository
            "psu"-> powerSupplyRepository
            "ram"-> randomAccessMemoryRepository
            "ssd"-> solidStateDriveRepository
            else -> throw Exception("Component category $componentCategory not found.")
        }
    }
}