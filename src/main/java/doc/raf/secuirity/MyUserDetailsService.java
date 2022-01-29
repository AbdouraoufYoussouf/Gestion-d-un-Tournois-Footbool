package doc.raf.secuirity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import doc.raf.dao.UserRepositoey;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepositoey userRepo;

    public MyUserDetailsService(UserRepositoey userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserPrincipal(userRepo.findByUsername(username));
    }

}