package Services.Implementations;

import Dao.Interfaces.ICityDao;
import Models.Entities.City;
import Services.Interfaces.ICityService;

import java.util.List;

public class CityService implements ICityService {

     ICityDao iCityDao ;
     public  CityService(ICityDao iCityDao ){
         this.iCityDao = iCityDao ;
     }

    @Override
    public List<City> getAllCities() {
       return iCityDao.getAllCities();
    }
}
