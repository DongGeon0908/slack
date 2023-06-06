package com.goofy.slack.config

import com.goofy.slack.client.ReactiveSlackClient
import com.goofy.slack.client.SlackClient
import com.goofy.slack.extension.WebClientExtension
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SlackConfig(
    private val slackProperties: SlackProperties
) {
    private val logger = mu.KotlinLogging.logger {}

    @Bean
    fun slackClient(): SlackClient {
        val webClient = WebClientExtension.generate(baseUrl = slackProperties.webhookUrl)
        logger.info { "initialized slack client" }
        return ReactiveSlackClient(webClient)
    }
}
