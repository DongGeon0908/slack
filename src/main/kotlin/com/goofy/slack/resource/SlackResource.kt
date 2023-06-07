package com.goofy.slack.resource

import com.goofy.slack.client.SlackClient
import com.goofy.slack.client.model.SlackMessageModel
import com.goofy.slack.config.SlackProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SlackResource(
    private val slackClient: SlackClient,
    private val slackProperties: SlackProperties
) {
    @GetMapping("/api/v1/slack")
    suspend fun sendMessage() =
        slackClient.send(
            webhookModel = slackProperties.getWebhookModel("test1"),
            slackMessageModel = SlackMessageModel("send slack message")
        )

    @GetMapping("/api/v1/slack-bulk")
    suspend fun sendBulkMessages() = slackClient.sendBulk(
        webhookModel = slackProperties.getWebhookModel("test2"),
        slackMessageModels = (0..50).map { count -> SlackMessageModel("send slack message - $count") }
    )
}
