package br.com.alpha.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alpha.bean.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	User findByUsername(String username);
}
