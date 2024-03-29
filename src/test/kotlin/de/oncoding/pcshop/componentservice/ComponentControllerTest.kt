package de.oncoding.pcshop.componentservice

import com.fasterxml.jackson.databind.ObjectMapper
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.motherBoard1
import de.oncoding.pcshop.componentservice.model.pccomponents.*
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
class ComponentControllerTest {
    
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
    fun `get all - finds all components`() {
        // given
        val chassis = testDataFactory.saveChassis()
        val cpu = testDataFactory.saveCpu()
        val cpuCooler = testDataFactory.saveCpuCooler()
        val graphicsCard = testDataFactory.saveGraphicsCard()
        val hdd = testDataFactory.saveHDD()
        val motherBoard = testDataFactory.saveMotherBoard()
        val psu = testDataFactory.savePSU()
        val ram = testDataFactory.saveRAM()
        val ssd = testDataFactory.saveSSD()

        // when
        val filterString = "chassis,cpu,cpuCooler,graphicsCard,hdd,motherBoard,psu,ram,ssd"
        val response = client.getForEntity("/api/v1/components?categories=$filterString", String::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        val objectMapper = ObjectMapper()
        assertThat(response.body).contains(
                objectMapper.writeValueAsString(chassis),
                objectMapper.writeValueAsString(cpu),
                objectMapper.writeValueAsString(cpuCooler),
                objectMapper.writeValueAsString(graphicsCard),
                objectMapper.writeValueAsString(hdd),
                objectMapper.writeValueAsString(motherBoard),
                objectMapper.writeValueAsString(psu),
                objectMapper.writeValueAsString(ram),
                objectMapper.writeValueAsString(ssd)
        )
    }

    @Test
    fun `get all - filters for chassis`() {
        // given
        testDataFactory.saveMotherBoard()
        val chassis = testDataFactory.saveChassis()

        // when
        val response = client.getForEntity("/api/v1/components?categories=chassis", Array<Chassis>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(chassis)
    }

    @Test
    fun `get all - filters for cpu`() {
        // given
        testDataFactory.saveMotherBoard()
        val cpu = testDataFactory.saveCpu()

        // when
        val response = client.getForEntity("/api/v1/components?categories=cpu", Array<Cpu>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(cpu)
    }

    @Test
    fun `get all - filters for cpuCooler`() {
        // given
        testDataFactory.saveMotherBoard()
        val cpuCooler = testDataFactory.saveCpuCooler()

        // when
        val response = client.getForEntity("/api/v1/components?categories=cpuCooler", Array<CpuCooler>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(cpuCooler)
    }

    @Test
    fun `get all - filters for graphicsCards`() {
        // given
        testDataFactory.saveMotherBoard()
        val graphicsCard = testDataFactory.saveGraphicsCard()

        // when
        val response = client.getForEntity("/api/v1/components?categories=graphicsCard", Array<GraphicsCard>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(graphicsCard)
    }


    @Test
    fun `get all - filters for hdd`() {
        // given
        testDataFactory.saveMotherBoard()
        val hdd = testDataFactory.saveHDD()

        // when
        val response = client.getForEntity(
                "/api/v1/components?categories=hdd",
                Array<HardDiskDrive>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(hdd)
    }

    @Test
    fun `get all - filters for motherBoards`() {
        // given
        testDataFactory.saveMotherBoard()
        testDataFactory.saveCpu()

        // when
        val response = client.getForEntity(
                "/api/v1/components?categories=motherBoard",
                Array<MotherBoard>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(motherBoard1)
    }

    @Test
    fun `get all - filters for power supply`() {
        // given
        testDataFactory.saveMotherBoard()
        val psu = testDataFactory.savePSU()

        // when
        val response = client.getForEntity("/api/v1/components?categories=psu", Array<PowerSupplyUnit>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(psu)
    }

    @Test
    fun `get all - filters for ram`() {
        // given
        testDataFactory.saveMotherBoard()
        val ram = testDataFactory.saveRAM()

        // when
        val response = client.getForEntity("/api/v1/components?categories=ram", Array<RandomAccessMemory>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(ram)
    }

    @Test
    fun `get all - filters for ssd`() {
        // given
        testDataFactory.saveMotherBoard()
        val ssd = testDataFactory.saveSSD()

        // when
        val response = client.getForEntity("/api/v1/components?categories=ssd", Array<SolidStateDrive>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(ssd)
    }
}
