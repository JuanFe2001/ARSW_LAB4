/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.eci.arsw.blueprints.test.persistence.impl;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.filters.impl.SubsamplingFiltering;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@SpringBootTest
public class SubsamplingFilteringTest {

    @Bean
    public BlueprintFilter subsamplingFiltering() {
        return new SubsamplingFiltering();
    }

    @Autowired
    private BlueprintFilter subsamplingFiltering;

    @Test
    public void testSubsamplingFiltering() {
        
        Point[] subsamplingPoints = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4) };
        Blueprint blueprint = new Blueprint("TestBlueprint", "TestAuthor", subsamplingPoints);

        
        Blueprint filteredBlueprint = subsamplingFiltering.bluePrintFiltering(blueprint);

     
        Point[] expectedPoints = { new Point(1, 1), new Point(3, 3) };
        assertEquals(blueprint.getName(), filteredBlueprint.getName());
        assertEquals(blueprint.getAuthor(), filteredBlueprint.getAuthor());
    }
}
