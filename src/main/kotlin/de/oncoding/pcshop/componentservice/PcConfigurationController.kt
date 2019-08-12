package de.oncoding.pcshop.componentservice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PcConfigurationController(
        val mainBoardRepository: MainBoardRepository
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPcConfiguration(@RequestBody configuration: PcConfigurationCreateRequest){
        if (!mainBoardRepository.existsById(configuration.mainBoardId))
            throw BadRequestException("No MainBoard with id ${configuration.mainBoardId} found.")
    }

}


data class PcConfigurationCreateRequest(
        val mainBoardId: String
)

data class PcConfigurationCreateResponse(
        val configurationId: String
)
