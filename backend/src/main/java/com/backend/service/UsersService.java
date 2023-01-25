package com.backend.service;

import com.backend.model.UsersModel;
import com.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private final UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder =  new BCryptPasswordEncoder();
    }

    public String registerUser(String firstName,String lastName,String email,String password){
        if(email==null && password==null) {
            return "null";
        }else{
            if(usersRepository.findFirstByEmail(email).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            String passwordEncoded = this.passwordEncoder.encode(password);
            UsersModel usersModel = new UsersModel();
            usersModel.setFirstName(firstName);
            usersModel.setLastName(lastName);
            usersModel.setEmail(email);
            usersModel.setPassword(passwordEncoded);

            // Aici il adaugam in baza de date
            usersRepository.save(usersModel);
            return "adaugat!";
        }
    }

    public UsersModel authenticate(String email,String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UsersModel usersModel = usersRepository.findByEmail(email);
        String passEncoded = usersModel.getPassword();
        if(bCryptPasswordEncoder.matches(password,passEncoded)) {
            return usersRepository.findByEmailAndPassword(email, passEncoded).orElse(null);
        }
        else return null;
    }

    public Iterable<UsersModel> listAll() {
        return this.usersRepository.findAll();
    }

    public String save(UsersModel usersModel) {
        String firstName=usersModel.getFirstName();
        String lastName=usersModel.getLastName();
        String email=usersModel.getEmail();
        String password=usersModel.getPassword();
        String response= registerUser(firstName,lastName,email,password);
        return response;
    }
}
