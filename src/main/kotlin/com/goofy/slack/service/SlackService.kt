package com.goofy.slack.service

import com.goofy.slack.client.SlackClient
import com.goofy.slack.client.model.SlackMessageModel
import com.goofy.slack.config.SlackProperties
import org.springframework.stereotype.Service

@Service
class SlackService(
    private val slackClient: SlackClient,
    private val slackProperties: SlackProperties,
) {
    suspend fun sendMessage(): String {
        val webhookModel = slackProperties.getWebhookModel("test1")
        val slackMessageModel = SlackMessageModel("send slack message")

        return slackClient.send(webhookModel, slackMessageModel)
    }

    suspend fun sendBulkMessages() {
        val webhookModel = slackProperties.getWebhookModel("test2")
        val slackMessageModels = (0..50).map { count ->
            SlackMessageModel("send slack message - $count")
        }

        slackClient.sendBulk(webhookModel, slackMessageModels)
    }
}
