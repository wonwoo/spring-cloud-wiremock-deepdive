package ml.wonwoo.springcloudwiremockdeepdive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringCloudWiremockDeepdiveApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudWiremockDeepdiveApplication>(*args)
}
