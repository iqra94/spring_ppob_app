package programmerzamannow.restful.a.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.a.model.RegisterUserRequestA;
import programmerzamannow.restful.a.model.UpdateUserRequestD;
import programmerzamannow.restful.a.model.UserResponseC;
import programmerzamannow.restful.a.repository.UserRepositoryA;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.a.security.BCrypt;
import programmerzamannow.restful.model.UpdateUserRequest;
import programmerzamannow.restful.model.UserResponse;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceA {

    @Autowired
    private UserRepositoryA userRepository;

    @Autowired
    private ValidationServiceB validationService;

    @Transactional
    public void register(RegisterUserRequestA request) {
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }

    public UserResponseC get(User user) {
        return UserResponseC.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Transactional
    public UserResponseC update(User user, UpdateUserRequestD request) {
        validationService.validate(request);

        log.info("REQUEST : {}", request);

        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        userRepository.save(user);

        log.info("USER : {}", user.getName());

        return UserResponseC.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

}

/** sebelum 01:29:54 - Login User API
 @Autowired
 private Validator validator;

 @Transactional
 public void register(RegisterUserRequestA request) {
 Set<ConstraintViolation<RegisterUserRequestA>> constraintViolations = validationService.validate(request);

 if (constraintViolations.size() != 0) {
 throw new ConstraintViolationException(constraintViolations);
 }

 if (userRepository.existsById(request.getUsername())) {
 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
 }

 User user = new User();
 user.setUsername(request.getUsername());
 user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
 user.setName(request.getName());

 userRepository.save(user);
 }
 */

/** sebelum 01:46:24 - Get User API

 @Autowired
 private UserRepositoryA userRepository;

 @Autowired
 private ValidationServiceB validator;

 @Transactional
 public void register(RegisterUserRequestA request) {
 validationService.validate(request);

 if (userRepository.existsById(request.getUsername())) {
 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
 }

 User user = new User();
 user.setUsername(request.getUsername());
 user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
 user.setName(request.getName());

 userRepository.save(user);
 }

 */

/** sebelum 02:04:59 - Update User API

 @Autowired
 private UserRepositoryA userRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public void register(RegisterUserRequestA request) {
 validationService.validate(request);

 if (userRepository.existsById(request.getUsername())) {
 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
 }

 User user = new User();
 user.setUsername(request.getUsername());
 user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
 user.setName(request.getName());

 userRepository.save(user);
 }

 public UserResponseC get(User user) {
 return UserResponseC.builder()
 .username(user.getUsername())
 .name(user.getName())
 .build();
 }
 */