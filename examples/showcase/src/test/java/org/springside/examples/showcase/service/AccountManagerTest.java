package org.springside.examples.showcase.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springside.examples.showcase.dao.UserDao;
import org.springside.examples.showcase.entity.User;
import org.springside.examples.showcase.service.AccountManager;
import org.springside.examples.showcase.service.ServiceException;
import org.springside.modules.test.security.shiro.ShiroTestUtils;

public class AccountManagerTest {

	private AccountManager accountManager;
	@Mock
	private UserDao mockUserDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ShiroTestUtils.mockSubject("foo");

		accountManager = new AccountManager();
		accountManager.setUserDao(mockUserDao);
	}

	@After
	public void tearDown() {
		ShiroTestUtils.clearSubject();
	}

	@Test
	public void saveUser() {
		User admin = new User();
		admin.setId(1L);

		User user = new User();
		user.setId(2L);
		user.setPlainPassword("123");

		//正常保存用户.
		accountManager.saveUser(user);

		//保存超级管理用户抛出异常.
		try {
			accountManager.saveUser(admin);
			fail("expected ServicExcepton not be thrown");
		} catch (ServiceException e) {
			//expected exception
		}
	}
}
