package com.example.demo.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.relation.RelationNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception_handling.ResourceNotFound;
import com.example.demo.models.usermodels;
import com.example.demo.payload.userpayload;
import com.example.demo.repository.userrepository;
import com.example.demo.services.userservices;
@Service
public class userservices_imp implements userservices {
	
	@Autowired
	userrepository repo;
	@Autowired
	ModelMapper modelmapper;

	@Override
	public userpayload addusers(userpayload pay) {
		usermodels u = this.dto_users(pay);
		usermodels savesusermodels = this.repo.save(u);
		return this.users_dto(savesusermodels);
	}

	@Override
	public userpayload updateusers(userpayload pay, int id) {
		usermodels u = this.repo.findById(id).orElseThrow(()->new ResourceNotFound("usermodels","id",id));
		u.setName(pay.getName());
		u.setEmail(pay.getEmail());
		u.setPassword(pay.getPassword());
		usermodels u1=this.repo.save(u);
		userpayload pay1=this.users_dto(u1);
		return pay1;
	}

	@Override
	public void deleteusers(int id) {
		usermodels u = this.repo.findById(id).orElseThrow(()->new ResourceNotFound("usermodels","id",id));
		this.repo.delete(u);

	}

	public usermodels dto_users(userpayload pay) {
		usermodels u = this.modelmapper.map(pay,usermodels.class);
		return u;
	}
	
	public userpayload users_dto(usermodels u) {
		userpayload pay=this.modelmapper.map(u,userpayload.class);
		return pay;
	}

	@Override
	public List<userpayload> getallusers() {
			List<usermodels> u =(List<usermodels>)this.repo.findAll();
			List<userpayload> pay= u.stream().map(user-> this.users_dto(user)).collect(Collectors.toList());
			return pay;
		}

	@Override
	public userpayload getbyid(int id) {
			usermodels u=this.repo.findById(id).orElseThrow(()-> new ResourceNotFound("usermodels","id", id));
			return this.users_dto(u);
		}
}
