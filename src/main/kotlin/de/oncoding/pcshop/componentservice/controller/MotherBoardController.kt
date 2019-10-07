package de.oncoding.pcshop.componentservice.controller

import de.oncoding.pcshop.componentservice.MotherBoardRepository
import de.oncoding.pcshop.componentservice.model.pccomponents.MotherBoard
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MotherBoardController(
        val motherBoardRepository: MotherBoardRepository
) {

    @GetMapping(value = ["/api/v1/motherboards"])
    fun getMotherboards(
            @RequestParam format: String
    ): List<MotherBoard> {
        return motherBoardRepository.findAll().filter {
            it.format.name.equals(format, ignoreCase = true)
        }
    }

}
