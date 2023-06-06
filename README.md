# Slack Client

> The service introduces the ability to deliver messages to Slack via webhooks.

### Slack Config

```yaml
slack:
    webhook-url: # that's webhook url
```

### Slack Client

**SlackClient**

```kotlin
interface SlackClient {
    /**
     * if you success to message, then return "ok"
     */
    suspend fun send(model: SlackMessageModel): String
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
