package kr.hs.dgsw.demo.Service;

import kr.hs.dgsw.demo.Domain.User;

import java.util.List;


public interface UserService {

    List<User> listAllUsers();
    User viewUser(Long userId);
    User addUser(User user);
    User updateUser(User user);
    Boolean deleteUser(Long userId);

}
