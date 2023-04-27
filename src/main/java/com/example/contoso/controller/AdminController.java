package com.example.contoso.controller;

import com.example.contoso.dto.request.UserRequest;
import com.example.contoso.dto.response.UserResponse;
import com.example.contoso.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/27/2023 11:23 AM
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bindingResult.getAllErrors()
                            .get(0)
                            .getDefaultMessage());
        }
        userService.registration(userRequest);
        return ResponseEntity.ok()
                .body("User successfully registered");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity
                .ok()
                .body(userService.getAllManagers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Успешно удален");
    }
}