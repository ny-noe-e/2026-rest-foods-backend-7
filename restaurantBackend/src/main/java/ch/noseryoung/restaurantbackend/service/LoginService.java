package ch.noseryoung.restaurantbackend.service;

import ch.noseryoung.restaurantbackend.repository.LoginRepository;
import ch.noseryoung.restaurantbackend.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository Repo;

    public boolean authenticate(String psw,String user){
        return Repo.isUserValid(user,psw);
    }

    public boolean doesUserExist(String user){
        return Repo.doesUserExist(user);
    }
}
