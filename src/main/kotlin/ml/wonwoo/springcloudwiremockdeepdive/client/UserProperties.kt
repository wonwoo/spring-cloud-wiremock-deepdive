package ml.wonwoo.springcloudwiremockdeepdive.client

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("user.api")
data class UserProperties(

    val uri: String
)