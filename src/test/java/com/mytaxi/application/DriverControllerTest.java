package com.mytaxi.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.application.controller.DriverController;
import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverDomainService;
import com.mytaxi.domain.GeoCoordinate;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DriverController.class)
class DriverControllerTest
{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DriverDomainService driverDomainService;

    @Test
    void getDriverById() throws Exception
    {
        Driver driver = newDriver();

        given(driverDomainService.find(driver.getId())).willReturn(driver);

        mvc.perform(get("/v1/drivers/{driverId}", driver.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is(driver.getUsername())))
            .andExpect(jsonPath("$.password", is(driver.getPassword())));

        verify(driverDomainService, times(1)).find(driver.getId());
        verifyNoMoreInteractions(driverDomainService);

    }


    @Test
    void getDriverWithWrongId() throws Exception
    {
        given(driverDomainService.find(1L)).willThrow(EntityNotFoundException.class);

        mvc.perform(get("/v1/drivers/{driverId}", 1)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());

        verify(driverDomainService, times(1)).find(1L);
        verifyNoMoreInteractions(driverDomainService);
    }


    @Test
    void createDriver() throws Exception
    {

//        DriverDTO driverDto = getMockDriverDtos().get(0);
//        Driver driver = newDriver();
//
//        when(driverService.create(driver)).thenReturn(driver);
//
//        mvc.perform(post("/v1/drivers").contentType(MediaType.APPLICATION_JSON_UTF8)
//            .content(asJsonString(driverDto)))
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$.username", is(driver.getUsername())))
//            .andExpect(jsonPath("$.password", is(driver.getPassword())));
    }


    @Test
    void deleteDriver() throws Exception
    {
        DriverDTO driverDto = getMockDriverDtos().get(0);

        doNothing().when(driverDomainService).delete(driverDto.getId());

        mvc.perform(delete("/v1/drivers/{driverId}", driverDto.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(driverDomainService, times(1)).delete(driverDto.getId());
    }


    @Test
    void updateLocation()
    {
    }


    @Test
    void findDrivers()
    {
    }

    List<DriverDTO> getMockDriverDtos()
    {

        DriverDTO driver1 = DriverDTO.newBuilder()
            .setId(1L)
            .setUsername("Driver1")
            .setPassword("Driver1pw")
            .setCoordinate(new GeoCoordinate(10, 10))
            .createDriverDTO();

        DriverDTO driver2 = DriverDTO.newBuilder()
            .setId(2L)
            .setUsername("Driver2")
            .setPassword("Driver2pw")
            .setCoordinate(new GeoCoordinate(20, 20))
            .createDriverDTO();

        DriverDTO driver3 = DriverDTO.newBuilder()
            .setId(3L)
            .setUsername("Driver3")
            .setPassword("Driver3pw")
            .setCoordinate(new GeoCoordinate(30, 30))
            .createDriverDTO();

        return Arrays.asList(driver1, driver2, driver3);

    }


    static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    private static Driver newDriver()
    {
        Driver driver = new Driver("john", "pa$$word");
        driver.setId(1L);
        driver.setOnlineStatus(OnlineStatus.ONLINE);
        driver.setCoordinate(new GeoCoordinate(90, 90));
        driver.setDeleted(false);
        return driver;
    }
}