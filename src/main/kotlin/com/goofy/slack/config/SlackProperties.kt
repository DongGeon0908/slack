package com.goofy.slack.config

import com.goofy.slack.exception.NotFoundSlackWebhookModelException
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@Validated
@Configuration
@ConfigurationProperties(prefix = "slack")
class SlackProperties {
    @field:NotEmpty
    var webhooks: Map<String, WebhookModel> = emptyMap()

    fun getWebhookModel(key: String): WebhookModel {
        return webhooks[key] ?: throw NotFoundSlackWebhookModelException(key)
    }
}

@Validated
data class WebhookModel(
    @field:NotEmpty
    var url: String = ""
)
