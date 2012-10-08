package com.osintegrators.example;

import java.util.List;

public interface AddressService { 

	void createAddress(Address address);

	Address getAddressByName(String expectedName);

	void deleteAddress(Address result);

	List<Address> getAllAddresses();

}
