package lk.ijse.instagramclone.repository;

import lk.ijse.instagramclone.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    boolean existsUserByEmail(String email);

    User findByEmail(String email);
}
