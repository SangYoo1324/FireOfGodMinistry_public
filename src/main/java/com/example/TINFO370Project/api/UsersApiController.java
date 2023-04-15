package com.example.TINFO370Project.api;

import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UsersApiController {
    private final UsersService usersService;

    @PostMapping("/api/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody Map<String,String>request){
        if(!request.get("email").contains("@")){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else
            usersService.subscribe(request.get("email"));
            return  ResponseEntity.status(HttpStatus.OK).body("subscribed");
    }

    @DeleteMapping("/api/users")
    public ResponseEntity<String> delete(@RequestBody Map<String,String>request){

          Users user =  usersService.delete(request.get("id"));
          if(user!=null)
        return  ResponseEntity.status(HttpStatus.OK).body("subscribed");
          else
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sonething wrong");
    }

    @PatchMapping("/api/users")
    public ResponseEntity<String> update(@RequestBody Map<String,String> request){
        Users user = usersService.update(request.get("emailAddr"), request.get("subType"));
        if(user!=null)
        return ResponseEntity.status(HttpStatus.OK).body("Subscribed");
        else
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sonething wrong");
    }

}
