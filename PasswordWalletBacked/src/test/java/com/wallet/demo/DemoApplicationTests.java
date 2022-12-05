package com.wallet.demo;

import com.wallet.demo.service.AuthServiceTest;
import com.wallet.demo.service.PasswordService;
import com.wallet.demo.service.PasswordServiceTest;
import com.wallet.demo.utils.JwtUtilsTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		JwtUtilsTest.class,
		AuthServiceTest.class,
		PasswordServiceTest.class
})
@SpringBootTest
class DemoApplicationTests {


}
