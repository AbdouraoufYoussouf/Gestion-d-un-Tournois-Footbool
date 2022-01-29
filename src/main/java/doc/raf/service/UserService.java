package doc.raf.service;

import doc.raf.entities.User;

public interface UserService {
    void register(final User user);

    boolean checkIfUserExist(final String username);
}
