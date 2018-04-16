package com.example;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.transaction.annotation.Transactional;


    @Repository
    @Transactional
    public class VehicleDAO
    {
        @PersistenceContext
        private EntityManager entityManager;

        public void create(vehicle vehicle)
        {
            entityManager.persist(vehicle);
            return;
        }

        public vehicle getById(int id)
        {
            return entityManager.find(vehicle.class, id);
        }

        public vehicle update(vehicle newVehicle)
        {
            entityManager.merge(newVehicle);
            return newVehicle;
        }

        public vehicle delete(int id)
        {
            vehicle veh = entityManager.find(vehicle.class, id);
            if (veh != null)
            {
                entityManager.remove(veh);
                return veh;
            }
            return null;
        }
        //For LastestVehicle
        public int getHighestId()
        {
            Query q = entityManager.createNativeQuery("select max(id) from vehicles");
            int highestId = (int) q.getSingleResult();
            return highestId;
        }
    }
