package Dao.Implementations;

import Config.Db;
import Dao.Interfaces.ICityDao;
import Models.Entities.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao implements ICityDao {

    private Connection connection ;

    public CityDao()
    {
        this.connection = Db.getInstance().getConnection();
    }

    @Override
    public List<City> getAllCities() {
        String sql="SELECT * FROM cities";
        List<City> cities = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery() ;
            while (resultSet.next()){
                City city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("cityname")
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
