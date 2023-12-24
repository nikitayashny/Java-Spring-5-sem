package bstu.yashny.nikitayashny_proj.service.interfaces;

import bstu.yashny.nikitayashny_proj.models.Car;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ICarService {
    @Transactional
    void deleteById(Long id) throws ServiceException;

    Car create(Car computerStuff)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    boolean existsByName(String name) throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    List<Car> getAll()throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;
    @Transactional
    void deleteByName(String name)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    Car getById(Long id)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    Car getByName(String name)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    @Transactional
    void updateCarById(
            Long id,
            String name,
            String description,
            int cost)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;
}
