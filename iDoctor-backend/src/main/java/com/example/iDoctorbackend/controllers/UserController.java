package com.example.iDoctorbackend.controllers;

import com.example.iDoctorbackend.extensions.ResourceNotFoundException;
import com.example.iDoctorbackend.models.Doctor;
import com.example.iDoctorbackend.models.User;
import com.example.iDoctorbackend.repositories.DoctorRepository;
import com.example.iDoctorbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UserController {


    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

//    @PostMapping("/users")
//    public User setUser(@RequestBody User user){
//
//        return userRepository.save(user);
//    }
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable long id){
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with the id: " +  id));
//        return ResponseEntity.ok(user);
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails){
//        User updateUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with the id: " + id));
//        updateUser.setFirstName(userDetails.getFirstName());
//        updateUser.setSecondName((userDetails.getSecondName()));
//        updateUser.setNumber(userDetails.getNumber());
//        userRepository.save(updateUser);
//        return ResponseEntity.ok(updateUser);
//    }
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with the id: " + id));
//        userRepository.delete(user);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    @DeleteMapping("/users")
//    public ResponseEntity<HttpStatus> deleteAll(){
//        userRepository.deleteAll();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/doctors/{doctorId}/user")
    public ResponseEntity<List<User>> getAllUserByDoctorId(@PathVariable(value = "doctorId") Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + doctorId);
        }

        List<User> tags = userRepository.findUsersByDoctors(doctorId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }


    @PostMapping("/doctors/{doctorId}/user")
    public ResponseEntity<User> addUser(@PathVariable(value = "doctorId") Long doctorId, @RequestBody User user) {
//          Optional<Doctor> doctor = doctorRepository.findById(doctorId);
          Optional<Object> patient = doctorRepository.findById(doctorId).map(doctor -> {
              doctor.setUsers(user.getUser_id());
              return userRepository.save(user);
          });
          return new ResponseEntity<>(patient, HttpStatus.CREATED);
//          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        User patient =  doctorRepository.findById(doctorId).map(doctor -> {
//            long userId = user.getUser_id();
//
//
//            if (userId != 0L) {
//                User _user = userRepository.findById(userId)
//                        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + userId));
//                doctor.setUsers((Set<User>) _user);
//                doctorRepository.save(doctor);
//                return _user;
//            }
//
//            // add and create new Tag
//            doctor.setUsers((Set<User>) user);
//            return userRepository.save(user);
//        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + doctorId));
//            return new ResponseEntity<>(patient, HttpStatus.CREATED);

    }

}
