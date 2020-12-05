package lk.ijse.instagramclone.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login_Logout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private
    Integer id;
    private Integer userId;
    private boolean status;

    public Login_Logout() {
    }

    public Login_Logout(Integer userId, boolean status) {
        this.userId = userId;
        this.status = status;
    }

    public Login_Logout(Integer id, Integer userId, boolean status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
