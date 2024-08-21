package com.practice.exceptionhandlers.controller;

import com.practice.exceptionhandlers.exception.IncorrectCountException;
import com.practice.exceptionhandlers.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dogs")
public class DogsInteractionController {
    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        happiness += 2;
        return Map.of("talk", "Гав!");
    }

    @GetMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
        if (count == null) {
            throw new IncorrectCountException("Параметр count равен null.");
        }
        if (count <= 0) {
            throw new IncorrectCountException("Параметр count имеет отрицательное значение.");
        }

        happiness += count;
        return Map.of("action", "Вильнул хвостом. ".repeat(count));
    }


    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(final IncorrectCountException e) {
        return new ErrorResponse(
                "Ошибка с параметром count.", e.getMessage()
        );
    }
}