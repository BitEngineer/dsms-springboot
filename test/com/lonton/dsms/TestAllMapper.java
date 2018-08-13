package com.lonton.dsms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.lonton.dsms.sys.mapper.TestUserMapper;

/**
 * 测试所有 mapper
 * @author 邓键
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestUserMapper.class
})
public class TestAllMapper {
	
}
