package org.springside.examples.showcase.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.showcase.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

	User findByName(String name);

	User findByLoginName(String loginName);
}
