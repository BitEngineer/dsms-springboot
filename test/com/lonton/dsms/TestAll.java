package com.lonton.dsms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试整个工程
 * @author 郭宇航
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestAllController.class,
	TestAllMapper.class,
	TestAllOthers.class
})
public class TestAll {
	
}
