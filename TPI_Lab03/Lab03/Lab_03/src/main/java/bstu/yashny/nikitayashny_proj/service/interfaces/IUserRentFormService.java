package bstu.yashny.nikitayashny_proj.service.interfaces;

import bstu.yashny.nikitayashny_proj.models.RentForm;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IUserRentFormService {
    @Transactional
    void deleteById(Long id)throws ServiceException;
    @Transactional
    void deleteByUserIdAndCarId(Long user_id, Long computerStuff_id) throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    RentForm create(RentForm userRentForm)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;
    boolean existsByCarId(Long computerStuff_id) throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;
    RentForm getById(Long id)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    List<RentForm> getAllByUserId(Long user_id)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;
    List<RentForm> getAllByRent(boolean rent)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;

    List<RentForm> getAllByCarExpirationDateLessThan(Date computerStuff_expirationDate)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;
    @Transactional
    void setUserRentFormById(Long id, boolean rent)throws ServiceException, bstu.yashny.nikitayashny_proj.exception.ServiceException;
}
