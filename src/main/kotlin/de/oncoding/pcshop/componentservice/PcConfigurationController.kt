package de.oncoding.pcshop.componentservice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PcConfigurationController(
        val motherBoardRepository: MotherBoardRepository
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPcConfiguration(@RequestBody configuration: PcConfigurationCreateRequest){
        if (!motherBoardRepository.existsById(configuration.motherBoardId))
            throw BadRequestException("No MotherBoard with id ${configuration.motherBoardId} found.")
    }

}


data class PcConfigurationCreateRequest(
        val motherBoardId: String
)

data class PcConfigurationCreateResponse(
        val configurationId: String
)
