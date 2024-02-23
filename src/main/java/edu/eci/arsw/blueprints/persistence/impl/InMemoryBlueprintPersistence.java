/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

/**
 *
 * @author Guardianes de la Galaxia
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

	private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

	public InMemoryBlueprintPersistence() {
		// load stub data
		Point[] pts = new Point[] { new Point(140, 140), new Point(115, 115) };
		Blueprint bp = new Blueprint("_authorname_", "_bpname_ ", pts);
		blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

	}

	@Override
	public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
		Set<Blueprint> bluePrints;
		if (!blueprints.isEmpty())
			bluePrints = new HashSet<>(blueprints.values());
		else
			throw new BlueprintNotFoundException("There are no registered plans.");
		return bluePrints;
	}

	@Override
	public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
		if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
			throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
		} else {
			blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
		}
	}

	@Override
	public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
		Blueprint bluePrint = null;
		boolean flag = false;
		for (Blueprint blueprint : blueprints.values()) {
			if ((blueprint.getName() == bprintname) && (blueprint.getAuthor() == author)) {
				bluePrint = blueprint;
				flag = true;
			}
		}
		if (!flag)
			throw new BlueprintNotFoundException("The plan you are looking for cannot be found.");

		return bluePrint;
	}

	@Override
	public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
		Set<Blueprint> blueprintsByAuthor = new HashSet<>();
		for (Tuple tuple : blueprints.keySet()) {
			if (tuple.getElem1() == author) {
				blueprintsByAuthor.add(blueprints.get(tuple));
			}
		}
		if (blueprintsByAuthor.isEmpty())
			throw new BlueprintNotFoundException("No plans have been found in that person's name.");
		return blueprintsByAuthor;
	}
}