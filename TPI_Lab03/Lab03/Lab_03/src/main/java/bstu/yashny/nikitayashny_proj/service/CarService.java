package bstu.yashny.nikitayashny_proj.service;

import bstu.yashny.nikitayashny_proj.exception.RepositoryException;
import bstu.yashny.nikitayashny_proj.exception.ServiceException;
import bstu.yashny.nikitayashny_proj.models.Car;
import bstu.yashny.nikitayashny_proj.repository.CarRepository;
import bstu.yashny.nikitayashny_proj.service.interfaces.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car create(Car Car)throws ServiceException {
        return carRepository.save(Car);
    }

    @Override
    public boolean existsByName(String name) throws ServiceException {
        try {
            return carRepository.existsByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getAll()throws ServiceException {
        return carRepository.findAll();
    }

    @Override
    public void deleteByName(String name)throws ServiceException {
        try {
            carRepository.deleteByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Car getById(Long id)throws ServiceException {
        try {
            return carRepository.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Car getByName(String name)throws ServiceException {
        try {
            return carRepository.getByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public void updateCarById(Long id, String name, String description, int cost ) throws ServiceException{
        try {
            carRepository.updateCarById(id, name, description, cost);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}
