package ml.wonwoo.springcloudwiremockdeepdive.mock

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import ml.wonwoo.springcloudwiremockdeepdive.client.UserClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import reactor.test.StepVerifier

@SpringBootTest(properties = ["user.api.uri=http://localhost:\${wiremock.server.port}"])
@AutoConfigureWireMock(port = 0)
class StubForTests(@Autowired private val userClient: UserClient) {

    @Test
    fun `mock stub for test`() {

        stubFor(get(urlEqualTo("/users"))
            .willReturn(aResponse().withHeader("Content-Type", "application/json")
                .withBody("""[{"name":"wonwoo", "email":"wonwoo@test.com"}, {"name":"foo", "email":"foo@test.com"}]""")));

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