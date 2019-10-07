package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.pccomponents.*
import de.oncoding.pcshop.componentservice.model.units.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestDataFactory(
        val motherBoardRepository: MotherBoardRepository,
        val chassisRepository: ChassisRepository,
        val cpuRepository: CpuRepository,
        val cpuCoolerRepository: CpuCoolerRepository,
        val graphicsCardRepository: GraphicsCardRepository,
        val hardDiskDriveRepository: HardDiskDriveRepository,
        val powerSupplyRepository: PowerSupplyRepository,
        val randomAccessMemoryRepository: RandomAccessMemoryRepository,
        val solidStateDriveRepository: SolidStateDriveRepository
) {

    fun saveChassis() = chassisRepository.save(chassis1)
    fun saveCpu(socket: CpuSocket = CpuSocket.AM4) = cpuRepository.save(cpu1.copy(
            id = UUID.randomUUID().toString(),
            cpuSocket = socket
    ))
    fun saveCpuCooler(
            sockets: Set<CpuSocket> = setOf(CpuSocket.AM4)
    ) = cpuCoolerRepository.save(cpuCooler1.copy(
            id = UUID.randomUUID().toString(),
            supportedCpuSockets = sockets
    ))
    fun saveGraphicsCard() = graphicsCardRepository.save(graphicsCard1)
    fun saveHDD() = hardDiskDriveRepository.save(hardDiskDrive)
    fun saveMotherBoard(format: MotherBoardFormat = MotherBoardFormat.MicroATX) = motherBoardRepository.save(motherBoard1.copy(
            id = UUID.randomUUID().toString(),
            format = format
    ))
    fun savePSU() = powerSupplyRepository.save(powerSupplyUnit1)
    fun saveRAM() = randomAccessMemoryRepository.save(ram1)
    fun saveSSD() = solidStateDriveRepository.save(solidStateDrive1)

    fun cleanAll(){
        chassisRepository.deleteAll()
        cpuRepository.deleteAll()
        cpuCoolerRepository.deleteAll()
        graphicsCardRepository.deleteAll()
        hardDiskDriveRepository.deleteAll()
        motherBoardRepository.deleteAll()
        powerSupplyRepository.deleteAll()
        randomAccessMemoryRepository.deleteAll()
        solidStateDriveRepository.deleteAll()
    }

    companion object {
        val motherBoard1 = MotherBoard(
                id = UUID.randomUUID().toString(),
                cpuSocket = CpuSocket.AM4,
                manufacturer = "MSI",
                model = "Z270 Gaming Pro Carbon",
                format = MotherBoardFormat.MicroATX,
                chipset = "Z270",
                maxRAMClockRateInMHz = MegaHerz(3800),
                ramType = RAMType.DDR4
        )

        val cpu1 = Cpu(
                id = UUID.randomUUID().toString(),
                cpuSocket = CpuSocket.AM4,
                model = "Ryzen 5 Quadcore 1500",
                manufacturer = "AMD",
                clockRateInMHz = MegaHerz(3400),
                numberOfCores = 4,
                wattage = Watts(65)
        )

        val cpuCooler1 = CpuCooler(
                id = UUID.randomUUID().toString(),
                supportedCpuSockets = setOf(CpuSocket.AM4),
                manufacturer = "Cooler Master",
                model = "MasterAir Pro 4",
                airFlowInCFM = CFM(66.7f)
        )

        val chassis1 = Chassis(
                id = UUID.randomUUID().toString(),
                model = "RVX01",
                manufacturer = "Silver Stone",
                motherBoardFormat = MotherBoardFormat.MicroATX,
                size = ChassisFormat.MidTower
        )

        val hardDiskDrive = HardDiskDrive(UUID.randomUUID().toString(),
                model = "Barracuda 500GB 32MB Cache",
                manufacturer = "Seagate",
                sizeInGB = GigaByte(500),
                transferRateInMBperS = MegaBytesPerSecond(210)
        )

        val graphicsCard1 = GraphicsCard(
                id = UUID.randomUUID().toString(),
                model = "Radeon RX 560",
                manufacturer = "ASUS",
                vramInGB = GigaByte(4),
                coreClockRateInMHz = MegaHerz(1750),
                wattage = Watts(75)
        )

        val ram1 = RandomAccessMemory(
                id = UUID.randomUUID().toString(),
                model = "Dominator Platinium 16GB 2800 MHz",
                manufacturer = "CORSAIR",
                sizeInGB = GigaByte(16),
                clockRateInMHz = MegaHerz(2800)
        )

        val powerSupplyUnit1 = PowerSupplyUnit(UUID.randomUUID().toString(),
                manufacturer = "Cooler Master",
                model = "MWE Bronze 450",
                wattage = Watts(450)
        )

        val solidStateDrive1 = SolidStateDrive(UUID.randomUUID().toString(),
                manufacturer = "Shean",
                model = "Mega 250GB",
                sizeInGB = GigaByte(250),
                transferRateInMBperS = MegaBytesPerSecond(480)
        )

    }
}
