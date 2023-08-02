package com.example.demo.services;

import java.util.List;

import com.example.demo.payload.userpayload;

public interface userservices {
	 userpayload addusers (userpayload pay);
	 userpayload updateusers(userpayload pay,int id);
	 void deleteusers(int id);
	 List<userpayload>getallusers();
	 userpayload getbyid(int id);
	 	 
}
