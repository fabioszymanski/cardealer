package com.car.dealer.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.car.dealer.dto.CarDTO;
import com.car.dealer.respository.CarRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("CarDealerService")
@RequestMapping(path = "/cardealer")
public class CarDealingController {
	
	@Autowired
	private CarRepository carRepository;
	
	/**
	 * Operation responsible for store car into the list (ok ok, it should be a database...)
	 * @param carDTO
	 */
	@ApiOperation(
        value = "Adds a new car to the list",
        notes = "Adds a cad to the list, so the rules must have been applied", 
        consumes = MediaType.APPLICATION_JSON_VALUE)
	
	@ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - The car was added to the list successfuly"),
            @ApiResponse(code = 400, message = "BAD_REQUEST - An invalid request was sent to the service"),
            @ApiResponse(code = 500, message = "SERVER_ERROR - Internal Server Error")
    })
	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public void addCar(@RequestBody CarDTO carDTO ) {
		carRepository.addCar(carDTO);
	}
	
	
	/**
	 * Operation resposible by retrieve the cars from the list accordingly the params informed
	 * @param makeModel
	 * @param year
	 * @return
	 */
	@ApiOperation(
	        value = "Accordingly the params informed, filter the cars stored",
	        notes = "The params makeModel and year must be informed so the search can work properly", 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/car", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<CarDTO> listCars(	
			@ApiParam(name = "makeModel", type = "String", value = "Make or Model of the car to be searched for")
			@RequestParam(required=false, name="makeModel") String makeModel,
			
			@ApiParam(name = "year", type = "Integer", value = "Year of the car to be searched for")
			@RequestParam(required=false, name="year") Integer year){
		return carRepository.getList(makeModel, year);
	}
	
	@ApiOperation(
	        value = "Calculates",
	        notes = "The params makeModel and year must be informed so the search can work properly", 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/rank", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CarDTO> getRecommendations(	
			@ApiParam(name = "priceFuel", type = "Double", value = "Price of the fuel per liter")
			@RequestParam(required=false, name="priceFuel") Double priceFuel,
			
			@ApiParam(name = "kmMonth", type = "Integer", value = "Quantity of km in a month that the car run")
			@RequestParam(required=false, name="kmMonth") Integer kmMonth){
		return carRepository.listRecommendations(priceFuel, kmMonth);
	}


	public CarRepository getCarRepository() {
		return carRepository;
	}


	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
}
