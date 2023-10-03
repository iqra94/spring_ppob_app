package programmerzamannow.restful.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import programmerzamannow.restful.a.model.LoginUserRequestB;
import programmerzamannow.restful.a.model.TokenResponseB;
import programmerzamannow.restful.a.model.WebResponseA;
import programmerzamannow.restful.a.service.AuthServiceB;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.LoginUserRequest;
import programmerzamannow.restful.model.TokenResponse;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.service.AuthService;

@RestController
public class AuthControllerB {

    @Autowired
    private AuthServiceB authService;

    @PostMapping(
            path = "/api/auth/login-b",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<TokenResponseB> login(@RequestBody LoginUserRequestB request) {
        TokenResponseB tokenResponse = authService.login(request);
        return WebResponseA.<TokenResponseB>builder().data(tokenResponse).build();
    }

    @DeleteMapping(
            path = "/api/auth/logout-e",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<String> logout(User user) {
        authService.logout(user);
        return WebResponseA.<String>builder().data("OK").build();
    }

}

/** sebelum 02:18:44 - Logout User API
 @Autowired
 private AuthServiceB authService;

 @PostMapping(
 path = "/api/auth/login-b",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<TokenResponseB> login(@RequestBody LoginUserRequestB request) {
 TokenResponseB tokenResponse = authService.login(request);
 return WebResponseA.<TokenResponseB>builder().data(tokenResponse).build();
 }

 */
