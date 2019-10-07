package de.oncoding.pcshop.componentservice

import de.oncoding.pcshop.componentservice.model.pccomponents.CpuCooler
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
class CpuCoolerControllerTest {
    
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
    fun `get cpucoolers - returns all CpuCoolers`() {
        // given
        val cooler1 = testDataFactory.saveCpuCooler(setOf(CpuSocket.AM4))
        val cooler2 = testDataFactory.saveCpuCooler(setOf(CpuSocket.LGA1151))
        testDataFactory.saveChassis()

        // when
        val response = client.getForEntity(
                "/api/v1/cpucoolers", Array<CpuCooler>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(cooler1, cooler2)
    }

    @Test
    fun `get cpucoolers - filters for socket`() {
        // given
        val cooler1 = testDataFactory.saveCpuCooler(setOf(CpuSocket.AM4))
        val cooler2 = testDataFactory.saveCpuCooler(setOf(CpuSocket.AM4, CpuSocket.LGA1151))
        testDataFactory.saveCpuCooler(setOf(CpuSocket.LGA1151))
        testDataFactory.saveChassis()

        // when
        val response = client.getForEntity(
                "/api/v1/cpucoolers?socket=am4", Array<CpuCooler>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(cooler1, cooler2)
    }

}
