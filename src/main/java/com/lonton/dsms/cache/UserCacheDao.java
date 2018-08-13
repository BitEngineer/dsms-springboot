package com.lonton.dsms.cache;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.lonton.dsms.sys.bean.Staff;
import com.lonton.dsms.sys.bean.StaffQueryDTO;
import com.lonton.dsms.sys.mapper.UserMapper;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/*
 * 注解方式使用Ehcache
 */
@Repository
@Lazy  // 该bean初始化时依赖于容器上下文，如不lazy加载，获取上下文为null
public class UserCacheDao {
	
	private static final String STAFF_CACHE = "staffCache";
	
	@Autowired
	private UserMapper userMapper;
	
	@PostConstruct
	public void init() throws SQLException {
		List<StaffQueryDTO> staffs = userMapper.select(null);
		Cache cache = EhcacheUtils.getCacheByName(STAFF_CACHE);
		for(StaffQueryDTO staff : staffs) {
			cache.put(new Element("staff_" + staff.getStaffId(), (Staff) staff));
		}
	}

	@CachePut(value=STAFF_CACHE, key="'staff_'+#staff.getStaffId()")
	public Staff insert(Staff staff) throws SQLException {
		userMapper.insert(staff);
		return staff;
	}
	
	public Staff selectById(String staffId) throws SQLException {
		Cache cache = EhcacheUtils.getCacheByName(STAFF_CACHE);
		Staff staffDto = (Staff) cache.get("staff_" + staffId).getObjectValue();
		return staffDto;
	}
}
