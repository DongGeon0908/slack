package com.goofy.slack.client

import com.goofy.slack.client.model.SlackMessageModel
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class ReactiveSlackClient(
    private val webclient: WebClient
) : SlackClient {
    override suspend fun send(model: SlackMessageModel): String {
        return webclient
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(model)
            .retrieve()
            .awaitBody()
    }
}
