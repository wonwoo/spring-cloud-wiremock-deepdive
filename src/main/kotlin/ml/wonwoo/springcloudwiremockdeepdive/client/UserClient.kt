package ml.wonwoo.springcloudwiremockdeepdive.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux

@Component
class UserClient(builder: WebClient.Builder, userProperties: UserProperties) {

    private val webClient = builder.baseUrl(userProperties.uri).build()

    fun getUsers(): Flux<UserInfo> =
        webClient
            .get()
            .uri("/users")
            .retrieve()
            .bodyToFlux()


}

data class UserInfo(

    val name: String,
    val email: String
)
