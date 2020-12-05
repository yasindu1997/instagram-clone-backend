package lk.ijse.instagramclone.repository;

import lk.ijse.instagramclone.entity.Login_Logout;
import org.springframework.data.repository.CrudRepository;

public interface LoginLogoutRepo extends CrudRepository<Login_Logout, Integer> {
    boolean existsByUserId(Integer id);

    Login_Logout findByUserId(Integer id);
}
