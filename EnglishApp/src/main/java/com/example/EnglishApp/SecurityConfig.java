// package com.example.EnglishApp;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     // @Bean
//     // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     //     http
//     //             .authorizeHttpRequests(request -> request
//     //                     .requestMatchers("/LK/**")
//     //                     .hasRole("LK_OWNER"))
//     //             .csrf(csrf -> csrf.disable())
//     //             .httpBasic(Customizer.withDefaults());
//     //     return http.build();
//     // }
//     // @Bean
//     // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     //     http.authorizeHttpRequests()
//     //     .anyRequest().authenticated()
//     //     .and().formLogin()
//     //     .loginPage("/login").permitAll();
//     //     return http.build();
//     // }

//     @Bean
//     public UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
//         User.UserBuilder users = User.builder();
//         UserDetails sarah = users
//             .username("sarah1")
//             .password(passwordEncoder.encode("abc123"))
//             .roles("LK_OWNER") // No roles for now
//             .build();

//         UserDetails hankOwnsNoCards = users
//             .username("hank-owns-no-cards")
//             .password(passwordEncoder.encode("qrs456"))
//             .roles("NON-OWNER") // new role
//             .build();
//         UserDetails kumar = users
//             .username("kumar2")
//             .password(passwordEncoder.encode("xyz789"))
//             .roles("LK_OWNER")
//             .build();
//         return new InMemoryUserDetailsManager(sarah, hankOwnsNoCards, kumar);
//     }

//     // @Autowired
//     // public void configureGlobal(AuthenticationManagerBuilder auth) 
//     //   throws Exception {
//     //     auth.inMemoryAuthentication().withUser("user")
//     //       .password(passwordEncoder().encode("password")).roles("USER");
//     // }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }