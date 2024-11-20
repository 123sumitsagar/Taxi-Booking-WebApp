package com.taxi.service;

public interface CheckAdminCredentialsService {
	public String checkAdminCredentials(String oldusername,String oldpassword);
	public String updateAdminCredentials(String newusername,String newpassword,String oldusername);

}
