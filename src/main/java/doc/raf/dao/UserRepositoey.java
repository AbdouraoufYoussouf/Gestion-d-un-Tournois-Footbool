package doc.raf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import doc.raf.entities.User;

public interface UserRepositoey extends JpaRepository<User ,Long> {
    public User findByUsername(String username);
}
