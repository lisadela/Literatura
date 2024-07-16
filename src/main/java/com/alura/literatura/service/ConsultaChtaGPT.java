package com.alura.literatura.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChtaGPT {


    public static String obtenerTraduccion(String texto) {
        OpenAiService service = new OpenAiService("sk-proj-xJQLxQAhmfafOyanCo2rT3BlbkFJAotPV5GK4qUnr2T2LCk6");


        CompletionRequest requisicion = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduce a español el siguiente texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        var respuesta = service.createCompletion(requisicion);
        return respuesta.getChoices().get(0).getText();
    }
}