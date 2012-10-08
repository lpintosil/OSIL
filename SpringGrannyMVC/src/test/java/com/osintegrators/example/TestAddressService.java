/**
 * 
 */
package com.osintegrators.example;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acoliver
 *
 */ 
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAddressService {
	@Autowired
	AddressService addressService;

	/** 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateAddress() {
		String expectedName = "John Doe";
		String expectedAddress = "345 West Main St\nDurham, NC";
		String expectedPhone = "+1.919.321.0119";
		String expectedEmail = "spam@osintegrators.com";
		Address address = createAddressObject(expectedName, expectedAddress, expectedPhone,expectedEmail);
		addressService.createAddress(address);
		Address result = addressService.getAddressByName(expectedName);
		
		assertEquals(expectedName, result.getName());
		assertEquals(expectedAddress,result.getAddress());
		assertEquals(expectedPhone,result.getPhone());
		assertEquals(expectedEmail,result.getEmail());	}
	
	@Test
	public void testGetAllAddresses() {
		String[] expectedName = {"John Doe","Sarah Palin", "John Edwards", "Joe Lieberman", "Jack Kemp", "Dan Quayle"};
		String[] expectedAddress = {"345 West Main St\nDurham, NC","Overlooking Russia\nJuneau","123 not at my house\nRaleigh, NC", "321 Not at my Party,\nHartford, CT", "545 Soccer is for Socialists,\nNY, NY", "62 You Say Tomato, I say Tomatoe,\nIndianapolis, IN"};
		String[] expectedPhone = {"+1.919.321.0119","999-9999", "199-9999", "991-9999", "999-9998", "113-1548"};
		String[] expectedEmail = {"spam@osintegrators.com","sarah@gop.org", "john@nowhere.com", "joe@nowhere.com", "jack@gop.org", "dan@gop.org"};
		
		for(int i = 1; i < expectedName.length; i++) { //ignore john doe for create
			Address address = createAddressObject(expectedName[i], expectedAddress[i], expectedPhone[i],expectedEmail[i]);
			addressService.createAddress(address);
		}
		
		List<Address> addresses = addressService.getAllAddresses();
		
		for(int i = 0; i < addresses.size(); i++) {
			Address address = addressService.getAddressByName(expectedName[i]);
			assertEquals(expectedName[i], address.getName());
			assertEquals(expectedAddress[i],address.getAddress());
			assertEquals(expectedPhone[i],address.getPhone());
			assertEquals(expectedEmail[i],address.getEmail());
		}
		assertEquals("there should be equal number of addresses as there are expectedName array entries", expectedName.length,addresses.size());
	}
	
	@Test
	public void testDeleteAddress() {
		String expectedName = "Lloyd Bentsen";
		String expectedAddress = "123 Mission way\nHidalgo, TX";
		String expectedPhone = "+1.999.999.9999";
		String expectedEmail = "lloyd@dnc.org";
		Address address = createAddressObject(expectedName, expectedAddress, expectedPhone,expectedEmail);
		addressService.createAddress(address);
		Address result = addressService.getAddressByName(expectedName);
		addressService.deleteAddress(result);
		result = addressService.getAddressByName(expectedName);
		assertNull("there should be no Lloyd Bentsen after the delete", result);
		
	}

	private Address createAddressObject(String name, String address,
			String phone, String email) {
		Address entity = new Address(name,address,phone,email);
		return entity;
	}

}
