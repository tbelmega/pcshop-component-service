package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.Cpu
import de.oncoding.pcshop.componentservice.model.Mainboard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MainboardRepository : JpaRepository<Mainboard, String>

@Repository
interface CpuRepository : JpaRepository<Cpu, String>