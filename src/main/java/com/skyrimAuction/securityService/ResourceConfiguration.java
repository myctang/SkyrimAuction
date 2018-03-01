package com.skyrimAuction.securityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {
    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(resourceId)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new OAuthRequestedMatcher())
                .anonymous().authorities("ROLE_ANON").and()
                .authorizeRequests()
                    .antMatchers("/").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/*").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/static/index.html").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/bone/*").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/styles/*").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/lib/**").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/public/**").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/favicon.ico").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/jquery-cookie/**").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/api/registerUser").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/api/get/**").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/updateItems/**").permitAll().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/admin").hasRole("ADMIN")
                .antMatchers("/static/index.html").hasAnyAuthority("ROLE_USER", "ROLE_ANON")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/**").authenticated()
                .antMatchers("/user/**").authenticated();
    }

    private static class OAuthRequestedMatcher implements RequestMatcher {
        public boolean matches(HttpServletRequest request) {
            String path = request.getServletPath();
            return path.startsWith("/api/");
        }
    }
}
