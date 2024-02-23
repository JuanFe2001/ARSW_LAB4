/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

/**
 *
 * @author hcadavid
 */
@Component
public class BlueprintsServices {

	@Autowired
	BlueprintsPersistence bpp;

	@Autowired
	BlueprintFilter bpf;

	public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
		this.bpp.saveBlueprint(bp);
	}

	/**
	 * 
	 * @return all the blueprints
	 * @throws BlueprintNotFoundException if there is no such blueprint
	 */
	public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
		return this.bpp.getAllBlueprints();
	}

	/**
	 * 
	 * @param author blueprint's author
	 * @param name   blueprint's name
	 * @return the blueprint of the given name created by the given author
	 * @throws BlueprintNotFoundException if there is no such blueprint
	 */
	public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
		return this.bpp.getBlueprint(author, name);

	}

	/**
	 * 
	 * @param author blueprint's author
	 * @return all the blueprints of the given author
	 * @throws BlueprintNotFoundException if the given author doesn't exist
	 */
	public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
		return this.bpp.getBlueprintsByAuthor(author);
	}

	public Blueprint bluePrintFiltering(Blueprint bp) {
		return bpf.bluePrintFiltering(bp);
	}

}
