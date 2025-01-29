package com.example.demo.utils;

import com.example.demo.dao.customer.CustomerRepository;
import com.example.demo.dao.user.UserRepository;
import com.example.demo.model.Customer;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {

            Optional<User> userOptional = userRepository.findByEmail(userDetails.getUsername());
            Optional<Customer> customerOptional = customerRepository.findByUserId(userOptional.get().getId());

            if (customerOptional != null) {
                HttpSession session = request.getSession();
                session.setAttribute("customerId", customerOptional.get().getId());

            }

        }
        response.sendRedirect("/");
    }
}