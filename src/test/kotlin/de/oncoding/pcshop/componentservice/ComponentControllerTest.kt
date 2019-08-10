package de.oncoding.pcshop.componentservice

import com.fasterxml.jackson.databind.ObjectMapper
import de.oncoding.pcshop.componentservice.model.*
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

    private val mainboard1 = MainBoard(
            id = UUID.randomUUID().toString(),
            cpuSocket = "AM4"
    )

    private val cpu1 = Cpu(
            id = UUID.randomUUID().toString(),
            cpuSocket = "AM4"
    )

    private val cpuCooler1 = CpuCooler(
            id = UUID.randomUUID().toString(),
            supportedCpuSockets = setOf("AM4")
    )

    @Test
    fun `get all - finds all components`() {
        // given
        val chassis = chassisRepository.save(Chassis(UUID.randomUUID().toString()))
        val cpu = cpuRepository.save(cpu1)
        val cpuCooler = cpuCoolerRepository.save(cpuCooler1)
        val graphicsCard = graphicsCardRepository.save(GraphicsCard(UUID.randomUUID().toString()))
        val hdd = hardDiskDriveRepository.save(HardDiskDrive(UUID.randomUUID().toString()))
        mainBoardRepository.save(mainboard1)
        val psu = psuRepository.save(PowerSupplyUnit(UUID.randomUUID().toString()))
        val ram = randomAccessMemoryRepository.save(RandomAccessMemory(UUID.randomUUID().toString()))
        val ssd = ssdRepository.save(SolidStateDrive(UUID.randomUUID().toString()))

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
                objectMapper.writeValueAsString(mainboard1),
                objectMapper.writeValueAsString(psu),
                objectMapper.writeValueAsString(ram),
                objectMapper.writeValueAsString(ssd)
        )
    }

    @Test
    fun `get all - filters for chassis`() {
        // given
        mainBoardRepository.save(mainboard1)
        val chassis = chassisRepository.save(Chassis(UUID.randomUUID().toString()))

        // when
        val response = client.getForEntity("/api/v1/components?categories=chassis", Array<Chassis>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(chassis)
    }

    @Test
    fun `get all - filters for cpu`() {
        // given
        mainBoardRepository.save(mainboard1)
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
        mainBoardRepository.save(mainboard1)
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
        mainBoardRepository.save(mainboard1)
        val graphicsCard = graphicsCardRepository.save(GraphicsCard(UUID.randomUUID().toString()))

        // when
        val response = client.getForEntity("/api/v1/components?categories=graphicsCard", Array<GraphicsCard>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(graphicsCard)
    }


    @Test
    fun `get all - filters for hdd`() {
        // given
        mainBoardRepository.save(mainboard1)
        val hdd = hardDiskDriveRepository.save(HardDiskDrive(UUID.randomUUID().toString()))

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
        mainBoardRepository.save(mainboard1)
        cpuRepository.save(cpu1)

        // when
        val response = client.getForEntity(
                "/api/v1/components?categories=mainboard",
                Array<MainBoard>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(mainboard1)
    }

    @Test
    fun `get all - filters for power supply`() {
        // given
        mainBoardRepository.save(mainboard1)
        val psu = psuRepository.save(PowerSupplyUnit(UUID.randomUUID().toString()))

        // when
        val response = client.getForEntity("/api/v1/components?categories=psu", Array<PowerSupplyUnit>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(psu)
    }

    @Test
    fun `get all - filters for ram`() {
        // given
        mainBoardRepository.save(mainboard1)
        val ram = randomAccessMemoryRepository.save(RandomAccessMemory(UUID.randomUUID().toString()))

        // when
        val response = client.getForEntity("/api/v1/components?categories=ram", Array<RandomAccessMemory>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(ram)
    }

    @Test
    fun `get all - filters for ssd`() {
        // given
        mainBoardRepository.save(mainboard1)
        val ssd = ssdRepository.save(SolidStateDrive(UUID.randomUUID().toString()))

        // when
        val response = client.getForEntity("/api/v1/components?categories=ssd", Array<SolidStateDrive>::class.java)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsExactlyInAnyOrder(ssd)
    }
}
