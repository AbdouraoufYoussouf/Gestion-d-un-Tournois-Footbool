package doc.raf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import doc.raf.dao.UserRepositoey;
import doc.raf.entities.User;
@Service
public class UserServiceImplement implements UserService{
    @Autowired
    UserRepositoey userRepo;
    

    @Override
    public void register(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setRoles("ROLE_ADMIN");
        user.setActive(1);
        userRepo.save(user);

    }

    @Override
    public boolean checkIfUserExist(String username) {
        return userRepo.findByUsername(username) != null ? true : false;
    }
    
}
