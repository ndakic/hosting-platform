package uns.ac.rs.hostplatserver.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import uns.ac.rs.hostplatserver.security.RestAuthenticationEntryPoint;
import uns.ac.rs.hostplatserver.security.TokenAuthenticationFilter;
import uns.ac.rs.hostplatserver.security.TokenUtils;
import uns.ac.rs.hostplatserver.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //config.addAllowedOrigin("*");
        /*
         *  You'll then need to switch to allowedOriginPatterns instead of allowedOrigins 
         *  but that gives you an option to define more precisely the allowed domain patterns. 
         *  In the mean time, you might be able to work around by listing specific domains 
         *  if that's feasible.
         */
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Location", "X-Requested-With", "Authorization", "Cache-Control", "Content-Type", "X-Total-Count", "allowedOriginPatterns"));
   	    config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.OPTIONS.name(), HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/task/**").permitAll()
            .antMatchers(HttpMethod.POST, "/task/**").permitAll()
            .antMatchers(HttpMethod.PUT, "/task/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/task/**").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated().and()
            .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/auth/login");


    }
}
