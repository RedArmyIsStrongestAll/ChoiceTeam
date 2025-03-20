package ru.mephi.atomhack.Skaifom.choiceTeam.configurations.webfilters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mephi.atomhack.Skaifom.choiceTeam.ebtity.RoleUser;

import java.io.IOException;

@Slf4j
public class RoleFromHeaderFilter extends OncePerRequestFilter {

    private static final String ROLE_HEADER = "X-User-Role";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String roleHeader = request.getHeader(ROLE_HEADER);
        if (roleHeader != null) {
            try {
                int roleCode = Integer.parseInt(roleHeader);

                if (RoleUser.isValidRole(roleCode)) {
                    RoleUser roleEnum = RoleUser.fromCode(roleCode);

                    PreAuthenticatedAuthenticationToken authentication =
                            new PreAuthenticatedAuthenticationToken(roleEnum.getRoleCode(), null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                log.error("Ошибка при извлечении роли {}", e.getMessage());
            }
            ;
        }

        filterChain.doFilter(request, response);
    }
}
