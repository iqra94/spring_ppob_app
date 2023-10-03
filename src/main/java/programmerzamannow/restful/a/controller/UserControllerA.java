package programmerzamannow.restful.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.a.model.RegisterUserRequestA;
import programmerzamannow.restful.a.model.UpdateUserRequestD;
import programmerzamannow.restful.a.model.UserResponseC;
import programmerzamannow.restful.a.model.WebResponseA;
import programmerzamannow.restful.a.service.UserServiceA;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.RegisterUserRequest;
import programmerzamannow.restful.model.UpdateUserRequest;
import programmerzamannow.restful.model.UserResponse;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.service.UserService;

@RestController
public class UserControllerA {

    @Autowired
    private UserServiceA userService;

    @PostMapping(
            path = "/api/users-a",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<String> register(@RequestBody RegisterUserRequestA request) {
        userService.register(request);
        return WebResponseA.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/users/current-c",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<UserResponseC> get(User user) {
        UserResponseC userResponse = userService.get(user);
        return WebResponseA.<UserResponseC>builder().data(userResponse).build();
    }

    @PatchMapping(
            path = "/api/users/current-d",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<UserResponseC> update(User user, @RequestBody UpdateUserRequestD request) {
        UserResponseC userResponse = userService.update(user, request);
        return WebResponseA.<UserResponseC>builder().data(userResponse).build();
    }

}

/** sebelum 01:46:24 - Get User API
 @RestController
 public class UserControllerA {

 @Autowired
 private UserServiceA userService;

 @PostMapping(
 path = "/api/users-a",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<String> register(@RequestBody RegisterUserRequestA request) {
 userService.register(request);
 return WebResponseA.<String>builder().data("OK").build();
 }

 }
 */

/**  sebelum 02:04:59 - Update User API

 @Autowired
 private UserServiceA userService;

 @PostMapping(
 path = "/api/users-a",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<String> register(@RequestBody RegisterUserRequestA request) {
 userService.register(request);
 return WebResponseA.<String>builder().data("OK").build();
 }

 @GetMapping(
 path = "/api/users/current-c",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<UserResponseC> get(User user) {
 UserResponseC userResponse = userService.get(user);
 return WebResponseA.<UserResponseC>builder().data(userResponse).build();
 }

 */
