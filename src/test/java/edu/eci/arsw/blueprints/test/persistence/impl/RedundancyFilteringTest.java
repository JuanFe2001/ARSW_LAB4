/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.filters.impl.RedundancyFiltering;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@SpringBootTest
public class RedundancyFilteringTest {

    @Bean
    @Primary
    public BlueprintFilter redundancyFiltering() {
        return new RedundancyFiltering();
    }

    @Autowired
    private BlueprintFilter redundancyFiltering;

    @Test
    public void testRedundancyFiltering() {
       
        Point[] redundantPoints = { new Point(1, 1), new Point(2, 2), new Point(2, 2), new Point(3, 3), new Point(3, 3) };
        Blueprint blueprint = new Blueprint("TestBlueprint", "TestAuthor", redundantPoints);

 
        Blueprint filteredBlueprint = redundancyFiltering.bluePrintFiltering(blueprint);

 
        Point[] expectedPoints = { new Point(1, 1), new Point(2, 2), new Point(3, 3) };
        assertEquals(blueprint.getName(), filteredBlueprint.getName());
        assertEquals(blueprint.getAuthor(), filteredBlueprint.getAuthor());
    }
}
