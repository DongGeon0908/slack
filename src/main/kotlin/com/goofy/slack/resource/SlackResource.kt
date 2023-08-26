package com.goofy.slack.resource

import com.goofy.slack.service.SlackService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SlackResource(
    private val slackService: SlackService,
) {
    @GetMapping("/api/v1/slack")
    suspend fun sendMessage() = slackService.sendMessage()

    @GetMapping("/api/v1/slack-bulk")
    suspend fun sendBulkMessages() = slackService.sendBulkMessages()
}
