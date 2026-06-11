package ch.noseryoung.restaurantbackend.repository;
import ch.noseryoung.restaurantbackend.model.Menu;
import ch.noseryoung.restaurantbackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface LoginRepository extends JpaRepository<Users, String>  {


    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Users u WHERE u.username = :userName AND u.Password = :password")
    boolean isUserValid(@Param("userName")String user, @Param("password")String password);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Users u WHERE u.username = :userName ")
    boolean doesUserExist(@Param("userName")String user);
}
