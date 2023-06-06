package com.goofy.slack.resource

import com.goofy.slack.client.SlackClient
import com.goofy.slack.client.model.SlackMessageModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SlackResource(
    private val slackClient: SlackClient
) {
    @GetMapping("/api/v1/slack")
    suspend fun sendMessage() = slackClient.send(SlackMessageModel("send slack message"))
}