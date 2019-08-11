package de.oncoding.pcshop.componentservice

import com.fasterxml.jackson.databind.ObjectMapper
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.chassis1
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.cpu1
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.cpuCooler1
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.graphicsCard1
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.hardDiskDrive
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.mainBoard1
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.powerSupplyUnit1
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.ram1
import de.oncoding.pcshop.componentservice.TestDataFactory.Companion.solidStateDrive1
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
    lateinit var client: TestRestTemplate

    @Autowired
    lateinit var chassisRepository: ChassisRepository

    @Autowired
    lateinit var cpuRepository: CpuRepository

    @Autowired
    lateinit var cpuCoolerRepository: CpuCoolerRepository

    @Autowired
    lateinit var graphicsCardRepository: GraphicsCardRepository

    @Autowired
    lateinit var hardDiskDriveRepository: HardDiskDriveRepository

    @Autowired
    lateinit var mainBoardRepository: MainBoardRepository

    @Autowired
    lateinit var psuRepository: PowerSupplyRepository

    @Autowired
    lateinit var randomAccessMemoryRepository: RandomAccessMemoryRepository

    @Autowired
    lateinit var ssdRepository: SolidStateDriveRepository


    @Before
    @After
    fun cleanUpDb() {
        chassisRepository.deleteAll()
        cpuRepository.deleteAll()
        cpuCoolerRepository.deleteAll()
        graphicsCardRepository.deleteAll()
        hardDiskDriveRepository.deleteAll()
        mainBoardRepository.deleteAll()
        psuRepository.deleteAll()
        randomAccessMemoryRepository.deleteAll()
        ssdRepository.deleteAll()
    }

    @Test
    fun `get all - finds all components`() {
        // given
        val chassis = chassisRepository.save(chassis1)
        val cpu = cpuRepository.save(cpu1)
        val cpuCooler = cpuCoolerRepository.save(cpuCooler1)
        val graphicsCard = graphicsCardRepository.save(graphicsCard1)
        val hdd = hardDiskDriveRepository.save(hardDiskDrive)
        mainBoardRepository.save(mainBoard1)
        val psu = psuRepository.save(powerSupplyUnit1)
        val ram = randomAccessMemoryRepository.save(ram1)
        val ssd = ssdRepository.save(solidStateDrive1)

        // when
        val filterString = "chassis,cpu,cpuCooler,graphicsCard,hdd,mainboard,psu,ram,ssd"
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
                objectMapper.writeValueAsString(mainBoard1),
                objectMapper.writeValueAsString(psu),
                objectMapper.writeValueAsString(ram),
                objectMapper.writeValueAsString(ssd)
        )
    }

    @Test
    fun `get all - filters for chassis`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val chassis = chassisRepository.save(chassis1)

        // when
        val response = client.getForEntity("/api/v1/components?categories=chassis", Array<Chassis>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(chassis)
    }

    @Test
    fun `get all - filters for cpu`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val cpu = cpuRepository.save(cpu1)

        // when
        val response = client.getForEntity("/api/v1/components?categories=cpu", Array<Cpu>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(cpu)
    }

    @Test
    fun `get all - filters for cpuCooler`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val cpuCooler = cpuCoolerRepository.save(cpuCooler1)

        // when
        val response = client.getForEntity("/api/v1/components?categories=cpuCooler", Array<CpuCooler>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(cpuCooler)
    }

    @Test
    fun `get all - filters for graphicsCards`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val graphicsCard = graphicsCardRepository.save(graphicsCard1)

        // when
        val response = client.getForEntity("/api/v1/components?categories=graphicsCard", Array<GraphicsCard>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(graphicsCard)
    }


    @Test
    fun `get all - filters for hdd`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val hdd = hardDiskDriveRepository.save(hardDiskDrive)

        // when
        val response = client.getForEntity(
                "/api/v1/components?categories=hdd",
                Array<HardDiskDrive>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(hdd)
    }

    @Test
    fun `get all - filters for mainboards`() {
        // given
        mainBoardRepository.save(mainBoard1)
        cpuRepository.save(cpu1)

        // when
        val response = client.getForEntity(
                "/api/v1/components?categories=mainboard",
                Array<MainBoard>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(mainBoard1)
    }

    @Test
    fun `get all - filters for power supply`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val psu = psuRepository.save(powerSupplyUnit1)

        // when
        val response = client.getForEntity("/api/v1/components?categories=psu", Array<PowerSupplyUnit>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(psu)
    }

    @Test
    fun `get all - filters for ram`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val ram = randomAccessMemoryRepository.save(ram1)

        // when
        val response = client.getForEntity("/api/v1/components?categories=ram", Array<RandomAccessMemory>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(ram)
    }

    @Test
    fun `get all - filters for ssd`() {
        // given
        mainBoardRepository.save(mainBoard1)
        val ssd = ssdRepository.save(solidStateDrive1)

        // when
        val response = client.getForEntity("/api/v1/components?categories=ssd", Array<SolidStateDrive>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(ssd)
    }
}
