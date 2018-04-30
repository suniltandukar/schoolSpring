package com.spring.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.dao.CategoryDao;
import com.spring.model.FeeInvoiceModel;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("feeInvoice")
public class FeeInvoiceController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value = "/add")
	public String add(Model model){
		model.addAttribute("categorylist",categoryDao.getCategories());
		return "invoice/invoice";
	}
	
	@RequestMapping(value = "/review")
	public String review(@ModelAttribute FeeInvoiceModel feeInvoice, ModelMap model){
		
		System.out.println(feeInvoice.getStudent().getStudentid());
		System.out.println("invoking review()");
		model.addAttribute("feeInvoice",feeInvoice);
		return "invoice/printableInvoice";
	}
	
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute FeeInvoiceModel feeInvoice, SessionStatus status){
		System.out.println("invoking save()");
		System.out.println(feeInvoice);
		status.setComplete();
		System.out.println(feeInvoice);
		return "redirect:/invoice/add";
	}
	
	
	

}