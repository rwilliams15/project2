package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class vehicleController
{
    @Autowired
    private VehicleDAO vehicleDAO;

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public vehicle addVehicle(@RequestBody vehicle newVehicle) throws IOException
    {
        vehicleDAO.create(newVehicle);
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public vehicle getVehicle(@PathVariable("id") int id) throws IOException
    {
        return vehicleDAO.getById(id);
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public vehicle updateVehicle(@RequestBody vehicle updateVehicle) throws IOException
    {
        if (vehicleDAO.getById(updateVehicle.getId()) == null)
        {
            return null;
        }
        return vehicleDAO.update(updateVehicle);
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException
    {
        vehicle veh = vehicleDAO.delete(id);
        if (veh != null)
        {
            return new ResponseEntity("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity("Not Found", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<vehicle> getLatestVehicles() throws IOException
    {
        int highestId = vehicleDAO.getHighestId();
        if (highestId == 0)
        {
            return null;
        }
        int foundVehicles = 0;
        List<vehicle> latestVehicles = new ArrayList<vehicle>();

        for (int i = highestId; foundVehicles < 10; i--)
        {
            if (i == 0)
            {
                break;
            } else if (vehicleDAO.getById(i) != null)
            {
                latestVehicles.add(vehicleDAO.getById(i));
                foundVehicles++;
            }
        }
        return latestVehicles;
    }
}
