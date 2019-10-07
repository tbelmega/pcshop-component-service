package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.pccomponents.Cpu
import de.oncoding.pcshop.componentservice.model.units.CpuSocket
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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
class CpuControllerTest {
    
    @Autowired
    lateinit var testDataFactory: TestDataFactory

    @Autowired
    lateinit var client: TestRestTemplate

    @Before
    @After
    fun cleanUpDb() {
        testDataFactory.cleanAll()
    }

    @Test
    fun `get cpus - filters for socket`() {
        // given
        val am4Cpu = testDataFactory.saveCpu(CpuSocket.AM4)
        testDataFactory.saveCpu(CpuSocket.LGA1151)
        testDataFactory.saveChassis()

        // when
        val response = client.getForEntity(
                "/api/v1/cpus?socket=am4", Array<Cpu>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(am4Cpu)
    }

}
