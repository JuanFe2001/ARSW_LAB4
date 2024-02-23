package edu.eci.arsw.blueprints.filters;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;

@Service
public interface BlueprintFilter {

	public Blueprint bluePrintFiltering(Blueprint bp);

}
