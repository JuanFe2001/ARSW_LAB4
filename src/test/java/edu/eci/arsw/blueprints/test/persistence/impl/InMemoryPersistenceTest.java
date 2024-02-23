/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

	@Test
	public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
		InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

		Point[] pts0 = new Point[] { new Point(40, 40), new Point(15, 15) };
		Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

		ibpp.saveBlueprint(bp0);

		Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10) };
		Blueprint bp = new Blueprint("john", "thepaint", pts);

		ibpp.saveBlueprint(bp);

		assertNotNull("Loading a previously stored blueprint returned null.",
				ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

		assertEquals("Loading a previously stored blueprint returned a different blueprint.",
				ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

	}

	@Test
	public void saveExistingBpTest() {
		InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

		Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10) };
		Blueprint bp = new Blueprint("john", "thepaint", pts);

		try {
			ibpp.saveBlueprint(bp);
		} catch (BlueprintPersistenceException ex) {
			fail("Blueprint persistence failed inserting the first blueprint.");
		}

		Point[] pts2 = new Point[] { new Point(10, 10), new Point(20, 20) };
		Blueprint bp2 = new Blueprint("john", "thepaint", pts2);

		try {
			ibpp.saveBlueprint(bp2);
			fail("An exception was expected after saving a second blueprint with the same name and autor");
		} catch (BlueprintPersistenceException ex) {

		}

	}

	@Test
	public void getBlueprintTest() throws BlueprintNotFoundException, BlueprintPersistenceException {
		InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
		Point[] pts0 = new Point[] { new Point(40, 40), new Point(15, 15) };
		Point[] pts1 = new Point[] { new Point(41, 41), new Point(30, 30) };
		Blueprint bp1 = new Blueprint("Daniel", "Apartamento2", pts1);
		Blueprint bp0 = new Blueprint("Daniel", "Baño3", pts0);
		ibpp.saveBlueprint(bp0);
		assertEquals(ibpp.getBlueprint(bp0.getAuthor(), bp0.getName()), bp0);
		try {
			ibpp.getBlueprint(bp1.getAuthor(), bp1.getName());
		} catch (BlueprintNotFoundException ex) {
			assertEquals("The plan you are looking for cannot be found.", ex.getMessage());
		}
	}

	@Test
	public void getBlueprintsByAuthorTest() throws BlueprintNotFoundException, BlueprintPersistenceException {
		InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
		Point[] pts0 = new Point[] { new Point(40, 40), new Point(15, 15) };
		Point[] pts1 = new Point[] { new Point(41, 41), new Point(30, 30) };
		Blueprint bp1 = new Blueprint("Daniel", "Apartamento2", pts1);
		Blueprint bp0 = new Blueprint("Daniel", "Baño3", pts0);
		ibpp.saveBlueprint(bp0);
		ibpp.saveBlueprint(bp1);
		Set<Blueprint> blueprints = ibpp.getBlueprintsByAuthor("Daniel");
		assertEquals(ibpp.getBlueprintsByAuthor("Daniel"), blueprints);
		try {
			ibpp.getBlueprintsByAuthor("Jose");
		} catch (BlueprintNotFoundException ex) {
			assertEquals("No plans have been found in that person's name.", ex.getMessage());
		}
	}

}
