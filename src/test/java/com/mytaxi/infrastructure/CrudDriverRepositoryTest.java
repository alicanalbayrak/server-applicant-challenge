package com.mytaxi.infrastructure;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.OnlineStatus;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CrudDriverRepositoryTest
{

    @Autowired
    private CrudDriverRepository crudDriverRepository;


    @Test
    public void testNumberOfDrivers()
    {
        long numberOfDrivers = crudDriverRepository.count();

        assertThat(numberOfDrivers).isEqualTo(8);
    }


    @Test
    public void testFindOnlineDrivers()
    {
        List<Driver> onlineDrivers = crudDriverRepository.findByOnlineStatus(OnlineStatus.ONLINE);

        assertNotNull(onlineDrivers);
        assertEquals(4, onlineDrivers.size());
    }


    @Test
    public void testFindOfflineDrivers()
    {
        List<Driver> offlineDrivers = crudDriverRepository.findByOnlineStatus(OnlineStatus.OFFLINE);

        assertNotNull(offlineDrivers);
        assertEquals(4, offlineDrivers.size());
    }


    @Test
    public void testFindDriver()
    {
        long driverId = 1L;

        Optional<Driver> driverOptional = crudDriverRepository.findById(driverId);

        assertThat(driverOptional).isNotEmpty();

        Driver driver = driverOptional.get();
        assertThat(driver.getUsername()).isEqualTo("driver01");
        assertThat(driver.getPassword()).isEqualTo("driver01pw");
        assertThat(driver.getOnlineStatus()).isEqualTo(OnlineStatus.OFFLINE);
        assertThat(driver.getDeleted()).isFalse();
        assertThat(driver.getCoordinate()).isNull();
    }


    @Test
    public void testCreateDriver()
    {
        Driver driver = crudDriverRepository.save(Driver.create("tmpDriver", "tmpDriverPassword"));
        assertThat(driver).isNotNull();
        assertThat(driver.getId()).isEqualTo(crudDriverRepository.findById(driver.getId()).get().getId());
        assertThat(crudDriverRepository.count()).isEqualTo(9);
    }


}