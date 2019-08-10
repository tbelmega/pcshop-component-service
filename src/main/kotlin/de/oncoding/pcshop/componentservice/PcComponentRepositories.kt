package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChassisRepository : JpaRepository<Chassis, String>

@Repository
interface CpuRepository : JpaRepository<Cpu, String>

@Repository
interface CpuCoolerRepository : JpaRepository<CpuCooler, String>

@Repository
interface GraphicsCardRepository : JpaRepository<GraphicsCard, String>

@Repository
interface HardDiskDriveRepository : JpaRepository<HardDiskDrive, String>

@Repository
interface MainBoardRepository : JpaRepository<MainBoard, String>

@Repository
interface PowerSupplyRepository : JpaRepository<PowerSupplyUnit, String>

@Repository
interface RandomAccessMemoryRepository : JpaRepository<RandomAccessMemory, String>

@Repository
interface SolidStateDriveRepository : JpaRepository<SolidStateDrive, String>
