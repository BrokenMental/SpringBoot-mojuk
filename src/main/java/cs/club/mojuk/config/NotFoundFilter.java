package cs.club.mojuk.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

//존재하지 않는 URL 요청 반환(404) 시 실행
@Component
public class NotFoundFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested resource was not found");
        }
    }
}
