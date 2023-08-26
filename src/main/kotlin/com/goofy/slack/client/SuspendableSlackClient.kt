package com.goofy.slack.client

import com.goofy.slack.client.model.SlackMessageModel
import com.goofy.slack.config.WebhookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class SuspendableSlackClient(
    private val webclient: WebClient
) : SlackClient {
    override suspend fun send(webhookModel: WebhookModel, slackMessageModel: SlackMessageModel): String {
        return withContext(Dispatchers.IO) {
            webclient
                .post()
                .uri(webhookModel.url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(slackMessageModel)
                .retrieve()
                .awaitBody()
        }
    }

    override suspend fun sendBulk(webhookModel: WebhookModel, slackMessageModels: List<SlackMessageModel>) {
        slackMessageModels.forEach { slackMessageModel ->
            coroutineScope {
                launch(Dispatchers.IO) { send(webhookModel, slackMessageModel) }
            }
        }
    }
}
