package com.goofy.slack.config

import com.goofy.slack.client.ReactiveSlackClient
import com.goofy.slack.client.SlackClient
import com.goofy.slack.extension.WebClientExtension
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SlackConfig {
    private val logger = mu.KotlinLogging.logger {}

    companion object {
        private const val SLACK_WEBHOOKS_DOMAIN = "https://hooks.slack.com/services"
    }

    @Bean
    fun slackClient(): SlackClient {
        val webClient = WebClientExtension.generate(baseUrl = SLACK_WEBHOOKS_DOMAIN)
        logger.info { "initialized slack client" }
        return ReactiveSlackClient(webClient)
    }
}
