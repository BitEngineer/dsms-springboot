package com.lonton.dsms;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring测试基类，所有依赖于Spring的测试类都要继承该类
 * @author 邓键
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/**/*.xml" })
@Transactional
//@Rollback(true)  // 默认,所以可以不必加
@WebAppConfiguration("WebContent")
public abstract class AbstractSpringTest {

}
