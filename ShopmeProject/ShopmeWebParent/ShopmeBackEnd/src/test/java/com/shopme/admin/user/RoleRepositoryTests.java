package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin=new Role("ADMIN","Manage Everything");
		Role savedRole=repo.save(roleAdmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestOfTheRoles() {
		Role salesPerson=new Role("SALESPERSON","Manage Product Price,Customers"
				                   +",Shipping,Orders and Sales Report");
		Role editor=new Role("EDITOR","Manage Categories,Brands,"
				                       +"Products,Articles and Menus");
		Role shipper=new Role("SHIPPER","View Products,View Orders,"
				                        +"Update Order Status");
		Role assistant=new Role("ASSISTANT","Manage Questions and Reviews");
		
		repo.saveAll(List.of(salesPerson,editor,shipper,assistant));
		
		
	}
}
