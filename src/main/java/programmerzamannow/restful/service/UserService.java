package programmerzamannow.restful.service;

import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.User;

public interface UserService {

  public User findUserById(Long userId) throws UserException;

  public User findUserProfileByJwt(String jwt) throws UserException;
}
