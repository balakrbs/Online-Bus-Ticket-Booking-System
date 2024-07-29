package com.onlinebus;

import com.onlinebus.Controller.AuthController;
import com.onlinebus.Model.Booking;
import com.onlinebus.Model.Users;
import com.onlinebus.Service.BookingService;
import com.onlinebus.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testShowLoginForm() {
        String result = authController.showLoginForm(model);
        assertEquals("login", result);
    }

    @Test
    void testRegisterUser() {
        Users user = new Users();
        authController.registerUser(user);
        verify(userService, times(1)).save(user);
    }

    @Test
    void testHome() {
        when(authentication.getName()).thenReturn("testUser");
        String result = authController.home(model);
        verify(model, times(1)).addAttribute("username", "testUser");
        assertEquals("home", result);
    }

    @Test
    void testLogout() {
        when(authentication.getName()).thenReturn("testUser");
        String result = authController.logout(request, response);
        assertEquals("redirect:/login?logout", result);
    }


    @Test
    void testUpdateUser() {
        Users user = new Users();
        user.setFirstname("John");
        user.setLastname("Doe");
        when(authentication.getName()).thenReturn("testUser");
        when(userService.findByUsername("testUser")).thenReturn(Optional.of(new Users()));

        String result = authController.updateUser(user);
        verify(userService, times(1)).updateUser(any(Users.class));
        assertEquals("redirect:/profile", result);
    }

    @Test
    void testAbout() {
        String result = authController.about(model);
        assertEquals("about", result);
    }
}
