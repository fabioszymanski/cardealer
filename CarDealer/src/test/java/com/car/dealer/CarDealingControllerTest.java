package com.car.dealer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.car.dealer.controller.CarDealingController;
import com.car.dealer.dto.CarDTO;
import com.car.dealer.respository.CarRepository;



public class CarDealingControllerTest {

	private CarRepository carRepository;
	private CarDealingController carDealingController;
	
	@BeforeEach
    void before() {
		carRepository = mock(CarRepository.class);
		
		carDealingController = new CarDealingController();
		
		CarRepository carRepo = new CarRepository();
		carRepo.setListCars(getMockSetCarDTO());
		
		carDealingController.setCarRepository(carRepo);
    }
	
	@Test
	public void testAddCar() {
		carDealingController.addCar(getMockCarDTO());
	}
	
	@Test
	public void testGet() {
		when(carRepository.getList(any(String.class), any(Integer.class))).thenReturn(getMockSetCarDTO());
		Set<CarDTO> list = carDealingController.listCars("", 2015);
		assertTrue(list.size()==2);
	}
	
	@Test
	public void testRecomendation() {
		List<CarDTO> list = carDealingController.getRecommendations(2.5, 100);
		assertTrue(list.get(0).getModel().equals("Corsa Sedan"));
	}
	

	private CarDTO getMockCarDTO() {
    	return new CarDTO("Aventador", "Lamborghini", "LP-500", 2022,  15000.00, 5.9, 9872.66);   	
    }
	
	private Set<CarDTO> getMockSetCarDTO() {
		Set<CarDTO> set = new HashSet<CarDTO>();
		set.add(new CarDTO("Aventador", "Lamborghini", "LP-500", 2022,  15000.00, 5.9, 9872.66));
		set.add(new CarDTO("Corsa Sedan", "Chevrolet", "bolinha", 1984,  1.00, 5.9, 9872.66));
    	return set;
    }	
}
