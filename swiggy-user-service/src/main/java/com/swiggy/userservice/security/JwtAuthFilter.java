package com.swiggy.userservice.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
 
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
 
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
 
        // Every request passes through here first - let's look at its headers
        final String authHeader = request.getHeader("Authorization");
 
        // If there's no "Authorization" header at all, or it doesn't start with "Bearer ",
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
 
        // Strip off "Bearer " (7 characters) to get just the raw token string
        final String jwt = authHeader.substring(7);
        final String userEmail = jwtUtil.extractEmail(jwt);
 
        // Only proceed if:
        //   - we actually got an email out of the token, AND
        //   - nobody's already been authenticated for this request yet
        // (that second check avoids redundant work if something upstream already handled it)
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
 
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
 
            // Now the real check - is this token actually valid for this user?
         
            if (jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {
 
           
                // it's basically saying "this request is now authenticated as this user"
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,                              // no credentials needed here, token already proved identity
                        userDetails.getAuthorities()        // their roles, e.g. ROLE_CUSTOMER
                );
 
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                
                // Every downstream controller/service can now ask "who's logged in?" and get an answer.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
 
        // Spring Security's rules (defined in SecurityConfig) will block it with a 401/403 later.
        filterChain.doFilter(request, response);
    }
}
 
