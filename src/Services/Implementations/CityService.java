package Services.Implementations;

import Dao.Interfaces.ICityDao;
import Models.Entities.City;
import Services.Interfaces.ICityService;

import java.util.List;

public class CityService implements ICityService {

    private ICityDao cityDao;
     public  CityService(ICityDao cityDao ){
         this.cityDao = cityDao ;
     }
     public CityService(){}

    @Override
    public List<City> getAllCities() {
       return cityDao.getAllCities();
    }
}
