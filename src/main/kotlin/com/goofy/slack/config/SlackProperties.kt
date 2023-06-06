package com.goofy.slack.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@Configuration
@Validated
@ConfigurationProperties(prefix = "slack")
class SlackProperties {
    @field:NotEmpty
    var webhookUrl: String = ""
}
