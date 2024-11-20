package com.taxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.taxi.model.BookingForm;
import com.taxi.model.ContactForm;
import com.taxi.model.ServiceForm;
import com.taxi.service.BookingFormService;
import com.taxi.service.CheckAdminCredentialsService;
import com.taxi.service.ContactFormService;
import com.taxi.service.ServiceFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ContactFormService contactFormService;
	private BookingFormService bookingFormService;
	private CheckAdminCredentialsService checkAdminCredentialsService;
	private ServiceFormService serviceFormService;
	
	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}


	@Autowired
	public void setCheckAdminCredentialsService(CheckAdminCredentialsService checkAdminCredentialsService) {
		this.checkAdminCredentialsService = checkAdminCredentialsService;
	}


	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}


	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}


	@GetMapping("dashboard")
	public String adminView() {
		return "admin/dashboard";
	}
	
	@GetMapping("readAllContacts")
	public String readAllContactView(Model model) {
		List<ContactForm> allContactsService = contactFormService.readAllContactsService();
		model.addAttribute("allcontacts", allContactsService);
		return "admin/readAllContacts";
	}
	@GetMapping("readAllBookings")
	public String readAllBookingView(Model model) {
		List<BookingForm> allBookingsService = bookingFormService.readAllBookingsService();
		model.addAttribute("allbookings", allBookingsService);
		
		return "admin/readAllBookings";
		
	}
	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id,RedirectAttributes redirectAttributes) {
		bookingFormService.deleteBookingService(id);
		redirectAttributes.addFlashAttribute("message","BOOKING DELETED SUCCESSFULLY");
		return "redirect:/admin/readAllBookings";
		
	}
	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id,RedirectAttributes redirectAttributes) {
		contactFormService.deleteContactService(id);
		redirectAttributes.addFlashAttribute("message","CONTACT DELETED SUCCESSFULLY");
		return "redirect:/admin/readAllContacts";
		
	}
	@GetMapping("changeCredentials")
	public String changeCredentialsView() {
		return "admin/changecredentials";
		
	}
	@PostMapping("changeCredentials")
	public String changeCredentials(
			@RequestParam("oldusername") String oldusername,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newusername") String newusername,
			@RequestParam("newpassword") String newpassword,
			RedirectAttributes redirectAttributes
		) {
		
		String results = checkAdminCredentialsService.checkAdminCredentials(oldusername, oldpassword);
		System.out.println(results);
		if(results.equals("SUCCESS")) {
			//PASSWORD UPDATE
			results=checkAdminCredentialsService.updateAdminCredentials(newusername, newpassword, oldusername);
			redirectAttributes.addFlashAttribute("message",results);
		}
		else {
			redirectAttributes.addFlashAttribute("message",results);
		}
		return "redirect:/admin/dashboard";
		
	}
	@GetMapping("addService")
	public String AddServiceView() {
		return "admin/addservice";
		
	}
	
	@InitBinder
	public void StopBinding(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("addService")
	public String AddServiceData(@ModelAttribute ServiceForm serviceForm ,
			@RequestParam("image") MultipartFile multipartFile,RedirectAttributes redirectAttributes) {
		
		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		try {
			ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
			if(service!=null) {
				redirectAttributes.addFlashAttribute("msg","Service Added Successfully");
			}else {
				redirectAttributes.addFlashAttribute("msg","Service Addition Failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("msg","Something went wrong");
		}
		
		return "redirect:/admin/addService";
		
	}

}
