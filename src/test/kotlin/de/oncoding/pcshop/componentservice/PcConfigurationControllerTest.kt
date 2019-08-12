package de.oncoding.pcshop.componentservice

import org.assertj.core.api.Assertions.assertThat
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
class PcConfigurationControllerTest {

    @Autowired
    private lateinit var client: TestRestTemplate

    @Autowired
    private lateinit var testDataFactory: TestDataFactory

    @Test
    fun `store a configuration - OK`() {
        // given
        testDataFactory.saveMainBoard()
        val pcConfigurationCreateRequest = PcConfigurationCreateRequest(TestDataFactory.mainBoard1.id)

        // when
        val response = client.postForEntity(
                "/api/v1/pcconfigurations",
                pcConfigurationCreateRequest,
                PcConfigurationCreateResponse::class.java
        )

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    fun `store a configuration - mainboard doesnt exist - BAD REQUEST`() {
        // given
        val pcConfigurationCreateRequest = PcConfigurationCreateRequest(TestDataFactory.mainBoard1.id)

        // when
        val response = client.postForEntity(
                "/api/v1/pcconfigurations",
                pcConfigurationCreateRequest,
                ErrorInfo::class.java
        )

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

}
