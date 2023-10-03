package programmerzamannow.restful.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.a.model.LoginUserRequestB;
import programmerzamannow.restful.a.model.TokenResponseB;
import programmerzamannow.restful.a.repository.UserRepositoryA;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.security.BCrypt;

import java.util.UUID;

@Service
public class AuthServiceB {

    @Autowired
    private UserRepositoryA userRepository;

    @Autowired
    private ValidationServiceB validationService;

    @Transactional
    public TokenResponseB login(LoginUserRequestB request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return TokenResponseB.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }

    @Transactional
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }

}

/** sebelum 02:18:44 - Logout User API

 @Autowired
 private UserRepositoryA userRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public TokenResponseB login(LoginUserRequestB request) {
 validationService.validate(request);

 User user = userRepository.findById(request.getUsername())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

 if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
 user.setToken(UUID.randomUUID().toString());
 user.setTokenExpiredAt(next30Days());
 userRepository.save(user);

 return TokenResponseB.builder()
 .token(user.getToken())
 .expiredAt(user.getTokenExpiredAt())
 .build();
 } else {
 throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
 }
 }

 private Long next30Days() {
 return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
 }

 */
