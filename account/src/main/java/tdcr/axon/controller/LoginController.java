package tdcr.axon.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tdcr.axon.command.CreateUserCommand;
import tdcr.axon.command.UpdateUserCommand;

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

    @RequestMapping(value = "/login/{userId}/{name}/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable("userId") String userId,@PathVariable("name") String name){
        this.commandGateway.send(new UpdateUserCommand(userId,name));
    }

}
