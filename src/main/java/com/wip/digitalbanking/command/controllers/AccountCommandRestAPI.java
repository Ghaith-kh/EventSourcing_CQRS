package com.wip.digitalbanking.command.controllers;

import com.wip.digitalbanking.command.dto.CreateAccountRequestDTO;
import com.wip.digitalbanking.command.dto.CreditAccountRequestDTO;
import com.wip.digitalbanking.command.dto.DebitAccountRequestDTO;
import com.wip.digitalbanking.coreapi.commands.CreateAccountCommand;
import com.wip.digitalbanking.coreapi.commands.CreditAccountCommand;
import com.wip.digitalbanking.coreapi.commands.DebitAccountCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/commands/accounts")
@Slf4j
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class AccountCommandRestAPI {
    //commande gateway variable de axon

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture newAccount ( @RequestBody CreateAccountRequestDTO request ){
        log.info("CreateAccountRequestDTO"+request.getInitialBalance().toString());
        CompletableFuture<String> response = commandGateway.send(
                new CreateAccountCommand(   UUID.randomUUID().toString(),
                        request.getInitialBalance(),
                        request.getCurrency())
        );



    return response;}

    @PutMapping("/credit")
    public CompletableFuture creditAccount ( @RequestBody CreditAccountRequestDTO request ){
        log.info("CreateAccountRequestDTO"+request.getAmount().toString());
        CompletableFuture<String> response = commandGateway.send(
                new CreditAccountCommand(
                        request.getAccountId(),
                        request.getAmount() ,
                        request.getCurrency()));

    return
            response;
    }

    @PutMapping("/debit")
    public CompletableFuture creditAccount ( @RequestBody DebitAccountRequestDTO request ){
        log.info("CreateAccountRequestDTO"+request.getAmount().toString());
        CompletableFuture<String> response = commandGateway.send(
                new DebitAccountCommand(
                        request.getAccountId(),
                        request.getAmount() ,
                        request.getCurrency()));

        return
                response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

@GetMapping(path="/events/{accountId}")
    public Stream accountEvents ( @PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();

    }


}
