package kr.hs.dgsw.demo.Service;

import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp1 implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User viewUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if(found.isPresent())
            return null;
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.findById(user.getId())
                .map(u -> {
                    u.setUsername(Optional.ofNullable(user.getUsername()).orElse(u.getUsername()));
                    u.setEmail(Optional.ofNullable(user.getEmail()).orElse(u.getEmail()));
                    u.setPassword(Optional.ofNullable(user.getPassword()).orElse(u.getPassword()));
                    u.setProfileImagePath(Optional.ofNullable(user.getProfileImagePath()).orElse(u.getProfileImagePath()));
                    u.setProfileImageName(Optional.ofNullable(user.getProfileImageName()).orElse(u.getProfileImageName()));
                    return this.userRepository.save(u);
                })
                .orElse(null);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        User found = this.viewUser(userId);
        if(found != null){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
