package com.example;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MyTasks
{

    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 */2 * ? * *")
    public void addVehicle()
    {
        String makeModel = RandomStringUtils.randomAlphabetic(10);
        int year = ThreadLocalRandom.current().nextInt(1980, 2018);
        double retailPrice = ThreadLocalRandom.current().nextInt(10000, 35000);
        vehicle vehicle = new vehicle(0, makeModel, year, retailPrice);

        String url = "http://localhost:8080/addVehicle";
        restTemplate.postForObject(url, vehicle, vehicle.class);
    }

    @Scheduled(cron = "0 * * ? * *")
    public void deleteVehicle()
    {
        int deleteID = ThreadLocalRandom.current().nextInt(1, 101);

        String getUrl = "http://localhost:8080/getVehicle/" + deleteID;
        String deleteUrl = "http://localhost:8080/deleteVehicle/" + deleteID;

        vehicle veh = restTemplate.getForObject(getUrl, vehicle.class);
        if (veh != null)
        {
            restTemplate.delete(deleteUrl);
            System.out.println("Deleted: " + veh);
        }
    }

    @Scheduled(cron = "0 * * ? * *")
    public void updateVehicle()
    {
        int updateID = ThreadLocalRandom.current().nextInt(1, 101);
        vehicle newVehicle = new vehicle(updateID, "updated", 1997, 25000);

        String getUrl = "http://localhost:8080/getVehicle/" + updateID;
        String putUrl = "http://localhost:8080/updateVehicle/";

        vehicle veh = restTemplate.getForObject(getUrl, vehicle.class);
        if (veh != null) {
            restTemplate.put(putUrl, newVehicle, vehicle.class);
            System.out.println("Updated Vehicle: " + veh);
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    public void latestVehicleReport()
    {
        String getUrl = "http://localhost:8080/getLatestVehicles/";
        List<vehicle> latestVehicles = restTemplate.getForObject(getUrl, List.class);
        System.out.println("Latest Vehicle");
        for (int i = 0; i < latestVehicles.size(); i++)
        {
            System.out.println(latestVehicles.get(i));
        }
        System.out.println("");
    }


}
