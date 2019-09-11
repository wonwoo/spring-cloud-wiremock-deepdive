package ml.wonwoo.springcloudwiremockdeepdive.mock

import ml.wonwoo.springcloudwiremockdeepdive.client.UserClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import reactor.test.StepVerifier

@SpringBootTest(properties = ["user.api.uri=http://localhost:\${wiremock.server.port}"])
@AutoConfigureWireMock(port = 0, stubs = ["classpath:/stubs/"])
class JsonFileForTests(@Autowired private val userClient: UserClient) {

    @Test
    fun `json stubs test`() {

        val users = this.userClient.getUsers()

        StepVerifier.create(users)
            .assertNext {
                assertThat(it.name).isEqualTo("wonwoo")
                assertThat(it.email).isEqualTo("wonwoo@test.com")
            }
            .assertNext {
                assertThat(it.name).isEqualTo("foo")
                assertThat(it.email).isEqualTo("foo@test.com")
            }
            .verifyComplete()

    }

}