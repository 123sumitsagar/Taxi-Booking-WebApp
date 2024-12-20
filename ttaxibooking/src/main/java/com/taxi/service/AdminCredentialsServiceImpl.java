package com.taxi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.taxi.dao.AdminDao;
import com.taxi.model.Admin;

@Service
public class AdminCredentialsServiceImpl implements CheckAdminCredentialsService {
	
	private AdminDao adminDao;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public String checkAdminCredentials(String oldusername, String oldpassword) {
		Optional<Admin> byUsername = adminDao.findByUsername(oldusername);
		if(byUsername.isPresent()) {
			Admin admin = byUsername.get();//sn,username,password
			boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());//check  oldpassword is same or not.
			if(matches) {
				return "SUCCESS";
			}else {
				return "Wrong old Credentials";
			}
		}else {
			return "Wrong old Credentials";
		}
			
	}


	@Override
	public String updateAdminCredentials(String newusername, String newpassword, String oldusername) {
		int updateCredentials = adminDao.updateCredentials(newusername,passwordEncoder.encode(newpassword) , oldusername);
		if(updateCredentials==1) {
			return "CREDENTIALS UPDATED SUCCESSFULLY";
		}else {
			return "FAILED TO UPDATE";
		}
		
	}
}
