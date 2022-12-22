package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;

// ��, ��, ���� �Է��ϸ� ������ �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC2 {
	// http://localhost/ch2/getYoilMVC?year=2021&month=10&day=1
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	// 1. �Է�
	@RequestMapping("/getYoilMVC2") 
	public String main(@RequestParam(required = true)int year,
			@RequestParam(required = true)int month, 
			@RequestParam(required = true)int day,
			Model model) throws IOException {
		// - ��ȿ�� �˻�
		if(!isValiad(year, month, day))
			return "yoilError";
		// 2. ���� ���
		char yoil = getYoil(year, month, day);
	    
	    // 3. ����� ����� model�� ����
	    model.addAttribute("year", year);
	    model.addAttribute("month", month);
	    model.addAttribute("day", day);
	    model.addAttribute("yoil", yoil);
	    
	    // 4. ���
	    return "yoil";	// /WEB-INF/views/yoil.jsp ���Ϸ� �̵�.

    }

	// �� Ŭ���� �ȿ����� ó���ؾ��ϱ� ������ private�� ����.
	private boolean isValiad(int year, int month, int day) {
		return true;
	}

	private char getYoil(int year, int month, int day) {
		// 2. ó��
	    Calendar cal = Calendar.getInstance();
	    cal.set(year, month - 1, day);
	
	    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	    return " �Ͽ�ȭ�������".charAt(dayOfWeek);   // �Ͽ���:1, ������:2, ... 
	}
}