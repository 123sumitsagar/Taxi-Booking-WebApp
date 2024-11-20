package com.taxi.controller;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taxi.model.BookingForm;
import com.taxi.model.ServiceForm;
import com.taxi.service.BookingFormService;
import com.taxi.service.ContactFormService;
import com.taxi.service.ServiceFormService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class Mycontroller {
	
	private ContactFormService contactFormService;
	private BookingFormService bookingFormService;
	private ServiceFormService serviceFormService;
	
	
	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}
	@Autowired
   public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

    @Autowired
	public void setContactFormService(ContactFormService contactFormService) {
	this.contactFormService = contactFormService;
}
	@GetMapping(path = {"/","home","welcome","index"})
	public String WelcomeView(HttpServletRequest req,Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("bookingForm",new BookingForm());
		return "index";
	}
		@GetMapping("about")
		public String AboutView(HttpServletRequest req,Model m) {
			String requestURI = req.getRequestURI();
			m.addAttribute("mycurrentpage",requestURI);
			return "about";
		}
		@GetMapping("cars")
		public String CarsView(HttpServletRequest req,Model m) {
			String requestURI = req.getRequestURI();
			m.addAttribute("mycurrentpage",requestURI);
			return "cars";
			}
		@GetMapping("services")
		public String ServicesView(HttpServletRequest req,Model m) {
			String requestURI = req.getRequestURI();
			m.addAttribute("mycurrentpage",requestURI);
			//DATA COLLECTION
			List<ServiceForm> allServices = serviceFormService.readAllServices();
			m.addAttribute("allservices",allServices);
			return "services";
			}
		@GetMapping("contacts")
		public String ContactsView(HttpServletRequest req,Model m) {
			String requestURI = req.getRequestURI();
			m.addAttribute("mycurrentpage",requestURI);
			m.addAttribute("contactForm",new com.taxi.model.ContactForm());
			return "contacts";
		}
		@GetMapping("/login")
		public String adminloginView(HttpServletRequest request,Model model) {
			ServletContext servletContext = request.getServletContext();
			Object attribute = servletContext.getAttribute("logout");
			if(attribute instanceof Boolean) {
				model.addAttribute("logout", attribute);
				servletContext.removeAttribute("logout");
				
			}
			
			return "adminlogin";
		}
		
		@PostMapping("contactform")
		public String ContactForm(@Valid @ModelAttribute com.taxi.model.ContactForm contactForm,
				BindingResult bindingResult,Model m,RedirectAttributes redirectAttributes) {
			if(bindingResult.hasErrors()){
				m.addAttribute("bindingResult",bindingResult);
				return "contacts";
				}
			 com.taxi.model.ContactForm contactFormServiceData = contactFormService.ContactFormServiceData(contactForm);
			 
			 if(contactFormServiceData!=null) {
				 redirectAttributes.addFlashAttribute("message","Message Sent Successfully");
			 }
			 else {
				 redirectAttributes.addFlashAttribute("message","Something Went Wrong");
			 }
			 
			System.out.println(contactForm);
			return "redirect:/contacts";
		}
		
		@PostMapping("bookingform")
		public String bookingForm(@Valid @ModelAttribute BookingForm bookingForm,
				BindingResult bindingResult,Model m,RedirectAttributes redirectAttributes) {
			
			if(bindingResult.hasErrors()){
				m.addAttribute("bindingResult",bindingResult);
				return "index";
				}
			
			
			else if(bookingForm.getAdult()+bookingForm.getChildren()>4) {
				m.addAttribute("message","The total no of adult and children cannot exceed 4");
				return "index";
			}
		    
			
			BookingForm bookMyData = bookingFormService.BookMyData(bookingForm);
			 if(bookMyData!=null) {
				 redirectAttributes.addFlashAttribute("message","Booking has Successfully completed");
			 }
			 else {
				 redirectAttributes.addFlashAttribute("message","Something Went Wrong");
			 }
			 
			
			System.out.println(bookingForm);
			//DAO

			return "redirect:/index";
		}
}
