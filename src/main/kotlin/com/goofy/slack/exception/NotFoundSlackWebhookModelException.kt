package com.goofy.slack.exception

class NotFoundSlackWebhookModelException(key: String) :
    RuntimeException("There is no data registered as the key[$key] value")
