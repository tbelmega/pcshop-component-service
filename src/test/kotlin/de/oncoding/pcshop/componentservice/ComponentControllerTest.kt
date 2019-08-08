package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.Cpu
import de.oncoding.pcshop.componentservice.model.Mainboard
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
class ComponentControllerTest {

    @Autowired
    lateinit var client: TestRestTemplate

    @Autowired
    lateinit var mainboardRepository: MainboardRepository

    @Autowired
    lateinit var cpuRepository: CpuRepository

//    @MockBean
//    lateinit var authService: AuthService



    @Before
    @After
    fun cleanUpDb() {
        mainboardRepository.deleteAll()
        cpuRepository.deleteAll()
    }


    @Test
    fun `get all - filters for mainboards`() {
        // given
        val mainboard = mainboardRepository.save(Mainboard(UUID.randomUUID().toString()))
        val cpu = cpuRepository.save(Cpu(UUID.randomUUID().toString()))

        // when
        val response = client.getForEntity(
                "/api/v1/components?categories=mainboard",
                Array<Mainboard>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(mainboard)
    }

    @Test
    fun `get all - filters for cpu`() {
        // given
        val mainboard = mainboardRepository.save(Mainboard(UUID.randomUUID().toString()))
        val cpu = cpuRepository.save(Cpu(UUID.randomUUID().toString()))

        // when
        val response = client.getForEntity("/api/v1/components?categories=cpu", Array<Cpu>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(cpu)
    }
}
