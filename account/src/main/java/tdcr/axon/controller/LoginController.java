package tdcr.axon.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tdcr.axon.command.CreateUserCommand;

@RestController
public class LoginController {

    private CommandGateway commandGateway;

    @Value("${application.message}")
    String message;

    @Value("${application.appname}")
    String appname;

    @Autowired
    public LoginController(CommandGateway commandGateway){
        this.commandGateway= commandGateway;
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!- " + message + " " + appname;
    }

    @RequestMapping(value = "/login/{userId}/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addUser(@PathVariable("userId") String userId){
        this.commandGateway.send(new CreateUserCommand(userId));
    }

}
