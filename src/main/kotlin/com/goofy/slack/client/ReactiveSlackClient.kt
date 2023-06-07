package com.goofy.slack.client

import com.goofy.slack.client.model.SlackMessageModel
import com.goofy.slack.config.WebhookModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class ReactiveSlackClient(
    private val webclient: WebClient
) : SlackClient {
    override suspend fun send(webhookModel: WebhookModel, slackMessageModel: SlackMessageModel): String {
        return webclient
            .post()
            .uri(webhookModel.url)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(slackMessageModel)
            .retrieve()
            .awaitBody()
    }

    override suspend fun sendBulk(webhookModel: WebhookModel, slackMessageModels: List<SlackMessageModel>) {
        slackMessageModels.forEach { slackMessageModel ->
            GlobalScope.launch {
                send(webhookModel, slackMessageModel)
            }
        }
    }
}
