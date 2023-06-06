package com.goofy.slack.client

import com.goofy.slack.client.model.SlackMessageModel

interface SlackClient {
    /**
     * if you success to message, then return "ok"
     */
    suspend fun send(model: SlackMessageModel): String
}
