package br.com.alpha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alpha.bean.User;
import br.com.alpha.repository.UserRepository;

@Service
public abstract class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
	
	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    	List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
    	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityListAdmin);
    }

}
