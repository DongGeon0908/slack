package com.goofy.slack.client

import com.goofy.slack.client.model.SlackMessageModel
import com.goofy.slack.config.WebhookModel

interface SlackClient {
    /**
     * if you success to message, then return "ok"
     */
    suspend fun send(webhookModel: WebhookModel, slackMessageModel: SlackMessageModel): String

    /**
     * support sending multiple messages, *Use after checking for overload*
     */
    suspend fun sendBulk(webhookModel: WebhookModel, slackMessageModels: List<SlackMessageModel>)
}
