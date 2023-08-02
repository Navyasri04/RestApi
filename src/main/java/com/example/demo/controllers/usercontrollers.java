package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception_handling.ApiResponse;
import com.example.demo.payload.userpayload;
import com.example.demo.services.userservices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class usercontrollers {
	@Autowired
	userservices service;
	@PostMapping("/insert")
	
	public ResponseEntity<userpayload>adduser(@Valid @RequestBody userpayload pay){
		userpayload users=this.service.addusers(pay);
		return new ResponseEntity<>(users,HttpStatus.CREATED);
	}
	
	@PutMapping("/update{id}")
	public ResponseEntity<userpayload>updateuser(@Valid @PathVariable int id, @RequestBody userpayload pay){
		userpayload user=this.service.updateusers(pay,id);
		return ResponseEntity.ok(user);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse>delete(@Valid @PathVariable int id){
	this.service.deleteusers(id);
	return new ResponseEntity<ApiResponse>(new ApiResponse("id deleted successfull", true),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<userpayload>> getall(){
		return ResponseEntity.ok(this.service.getallusers());	
	}
	@GetMapping("/getby/{id}")
	public ResponseEntity<userpayload>getbyid(@Valid @PathVariable int id){
		return ResponseEntity.ok(this.service.getbyid(id));
	}
	

}
