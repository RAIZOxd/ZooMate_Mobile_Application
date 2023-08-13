package com.example.zoo_application;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;

public interface BotReply
{
    void callback(DetectIntentResponse returnResponse);


}
