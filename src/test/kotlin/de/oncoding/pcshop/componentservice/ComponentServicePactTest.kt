package de.oncoding.pcshop.componentservice

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.loader.PactFolder
import au.com.dius.pact.provider.junit.target.TestTarget
import au.com.dius.pact.provider.spring.SpringRestPactRunner
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT


@AutoConfigureTestDatabase(replace = NONE)
@RunWith(SpringRestPactRunner::class)
@Provider("component-service")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@PactFolder("pacts")
class ComponentServicePactTest {

    @Autowired
    private lateinit var testDataFactory: TestDataFactory

    @Before
    fun setUp(){
        testDataFactory.cleanAll()
    }

    @JvmField
    @TestTarget
    final val target = SpringBootHttpTarget()

    @State("no coolers in the database")
    fun noCoolersState(){}

    @State("coolers in the database")
    fun coolersState(){
        testDataFactory.saveCpuCooler()
    }

}
