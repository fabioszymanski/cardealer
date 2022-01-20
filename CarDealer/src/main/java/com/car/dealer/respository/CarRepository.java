package com.car.dealer.respository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.web.context.annotation.ApplicationScope;

import com.car.dealer.dto.CarDTO;

/**
 * Class responsible by encapsulate the data access rules
 * @author Fábio Szymanski
 *
 */
@ApplicationScope
public class CarRepository {

	private Set<CarDTO> listCars = new HashSet<CarDTO>();	

	/**
	 * Add a new CarDTO object to the list
	 */
	public void addCar(CarDTO carDTO) {
		listCars.add(carDTO);
	}

	/**
	 * Search in the list by make or model and/or year
	 * @param makeModel
	 * @param year
	 * @return
	 */
	public Set<CarDTO> getList(String makeModel, Integer year){
		
	    Predicate<CarDTO> predicate = f -> (year != null && f.getYearRelease().equals(year)) 
	    									|| (makeModel != null && f.getMake().concat(f.getModel()).toLowerCase()
	    											.contains(makeModel.toLowerCase()));

	    	return listCars.stream()
				.filter(predicate)
				.collect(Collectors.toSet());
	}
	
	/**
	 * Calculates the ranking of each car added into the list
	 * @param priceFuel
	 * @param kmMonth
	 * @return
	 */
	public List<CarDTO> listRecommendations(Double priceFuel, Integer kmMonth){
		listCars.stream().forEach(car -> {

			// here i calculate the fuel costs for 4 year (48 months)
			car.setTotalCosts((car.getFuelConsumption() * priceFuel * kmMonth) * 48);

			// here i sum the costs of fuel with the costs of maintenance in 4 year
			car.setTotalCosts(car.getTotalCosts() + (car.getAnnualMaintenanceCosts() * 4 ));

		});

		return listCars.stream()
		.sorted(Comparator.<CarDTO>comparingDouble(x -> x.getPrice()) // here I sort by the price of the car lower->higher 				
				.thenComparing(x -> x.getFuelConsumption()) // here I sort by the fuel consumption lower->higher 
		          .thenComparingDouble(x -> x.getTotalCosts()) // here I sort by the fuel total costs lower->higher
				).collect(Collectors.toList());
	}

	public Set<CarDTO> getListCars() {
		return listCars;
	}

	public void setListCars(Set<CarDTO> listCars) {
		this.listCars = listCars;
	}
}
