package com.company.core.util;

import com.company.core.constant.AjaxReturnStatus;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ReturnUtil {

	public static String succeed() {
		AjaxReturnStatus b = new AjaxReturnStatus();
		b.setStatusCode(200);
		b.setMessage("成功");
		b.setCloseCurrent(true);
		return BeanUtils.object2Json(b);
	}
	public static String succeedDel() {
		AjaxReturnStatus b = new AjaxReturnStatus();
		b.setStatusCode(200);
		b.setMessage("成功");
		b.setCloseCurrent(false);
		return BeanUtils.object2Json(b);
	}

	public static String succeedForward(String forward){
		AjaxReturnStatus b = new AjaxReturnStatus();
		b.setStatusCode(200);
		b.setForward(forward);
		b.setCloseCurrent(true);
		return BeanUtils.object2Json(b);
	}

	public static String succeed(String message) {
		AjaxReturnStatus b = new AjaxReturnStatus();
		b.setStatusCode(200);
		b.setMessage(message);
		b.setCloseCurrent(true);
		return BeanUtils.object2Json(b);
	}
	public static String succeedDel(String message) {
		AjaxReturnStatus b = new AjaxReturnStatus();
		b.setStatusCode(200);
		b.setMessage(message);
		b.setCloseCurrent(false);
		return BeanUtils.object2Json(b);
	}

	public static String error(String message) {
		AjaxReturnStatus b = new AjaxReturnStatus();
		b.setStatusCode(300);
		b.setMessage(message);
		b.setCloseCurrent(false);
		return BeanUtils.object2Json(b);
	}

	public static void retJson(HttpServletResponse response, String jo){
		response.setContentType("application/json" + ";charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			response.getWriter().write(jo);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			log.error("返回json异常", e);
		}
	}
	public static void retJson(HttpServletRequest request, HttpServletResponse response, String jo){
		response.setContentType("application/json" + ";charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
			response.getWriter().write(jsonpCallback+"("+jo+")");//返回jsonp格式数据
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			log.error("返回json异常", e);
		}
	}



}
