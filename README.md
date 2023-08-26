# Slack Client

> The service introduces the ability to deliver messages to Slack via webhooks.

### Slack Config

```yaml
slack:
    webhook-url: # that's webhook url
```

### Slack Properties

```kotlin
@Validated
@Configuration
@ConfigurationProperties(prefix = "slack")
class SlackProperties {
    @field:NotEmpty
    var webhooks: Map<String, WebhookModel> = emptyMap()

    fun getWebhookModel(key: String): WebhookModel {
        return webhooks[key] ?: throw NotFoundSlackWebhookModelException(key)
    }
}

@Validated
data class WebhookModel(
    @field:NotEmpty
    var url: String = ""
)
```

### Slack Client

**SlackClient**

```kotlin
interface SlackClient {
    /**
     * if you success to message, then return "ok"
     */
    suspend fun send(model: SlackMessageModel): String

    /**
     * support sending multiple messages, *Use after checking for overload*
     */
    suspend fun sendBulk(models: List<SlackMessageModel>)
}
```

**SlackMessageModel**

```kotlin
data class SlackMessageModel(
    /**
     * Enter a message to be sent to Slack.
     */
    val text: String
)
```

### Slack Client impl

```kotlin
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
```

### Test Api

```kotlin
@RestController
class SlackResource(
    private val slackService: SlackService,
) {
    @GetMapping("/api/v1/slack")
    suspend fun sendMessage() = slackService.sendMessage()

    @GetMapping("/api/v1/slack-bulk")
    suspend fun sendBulkMessages() = slackService.sendBulkMessages()
}
```
