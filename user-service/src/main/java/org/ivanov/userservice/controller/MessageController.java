package org.ivanov.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ivanov.userservice.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/read")
    public void readMessage() {
        messageService.readMessages();
    }
}
