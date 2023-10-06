package programmerzamannow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmerzamannow.restful.config.JwtProvider;
import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtProvider jwtProvider;

  @Override
  public User findUserById(Long userId) throws UserException {
    Optional<User> user = userRepository.findById(userId);

    if (user.isPresent()) {
      return user.get();
    }

    throw new UserException("user not found with id - " +userId);
  }

  @Override
  public User findUserProfileByJwt(String jwt) throws UserException {
    String email = jwtProvider.getEmailFromToken(jwt);
    User user = userRepository.findByEmail(email);

    if (user == null) {
      throw new UserException("user not found with email - " +email);
    }
    return user;
  }

}
