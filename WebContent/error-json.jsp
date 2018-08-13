<%@page import="com.lonton.dsms.common.bean.BaseResponse"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="utf-8"%>
<% 
	// spring-mvc 中 SimpleMappingExceptionResolver 渲染视图时，
	// 会将Exception对象以key为DEFAULT_EXCEPTION_ATTRIBUTE(实际key值exception)存储起来
	Exception ex = (Exception) request.getAttribute("exception");
	String errorMsg = (null != ex ? ex.getMessage() : "抱歉，错误原因暂不清楚，请联系管理员解决。");
	
	BaseResponse<String> result = new BaseResponse<String>();
	result.setCodeWithDefaultMsg(BaseResponse.CODE_500);
	out.print(JSON.toJSONString(result));
%> 