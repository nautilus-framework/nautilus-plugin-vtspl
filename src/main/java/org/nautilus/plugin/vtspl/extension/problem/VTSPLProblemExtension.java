package org.nautilus.plugin.vtspl.extension.problem;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.nautilus.core.encoding.solution.NBinarySolution;
import org.nautilus.core.model.Instance;
import org.nautilus.core.objective.AbstractObjective;
import org.nautilus.plugin.extension.problem.AbstractProblemExtension;
import org.nautilus.plugin.vtspl.encoding.instance.TXTInstanceData;
import org.nautilus.plugin.vtspl.encoding.objective.AliveMutantsObjective;
import org.nautilus.plugin.vtspl.encoding.objective.CostObjective;
import org.nautilus.plugin.vtspl.encoding.objective.NumberOfProductsObjective;
import org.nautilus.plugin.vtspl.encoding.objective.SimilarityObjective;
import org.nautilus.plugin.vtspl.encoding.objective.UncoveredPairsObjective;
import org.nautilus.plugin.vtspl.encoding.objective.UnimportantFeaturesObjective;
import org.nautilus.plugin.vtspl.encoding.objective.UnselectedFeaturesObjective;
import org.nautilus.plugin.vtspl.encoding.problem.VTSPLProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.binarySet.BinarySet;

public class VTSPLProblemExtension extends AbstractProblemExtension {

	@Override
	public Problem<?> getProblem(Instance data, List<AbstractObjective> objectives) {
		return new VTSPLProblem(data, objectives);
	}

	@Override
	public String getName() {
		return "VTSPL Problem";
	}

	@Override
	public Class<? extends Solution<?>> supports() {
		return BinarySolution.class;
	}

	@Override
	public List<AbstractObjective> getObjectives() {
		
		List<AbstractObjective> objectives = new ArrayList<>();

		objectives.add(new NumberOfProductsObjective());
		objectives.add(new AliveMutantsObjective());
		objectives.add(new UncoveredPairsObjective());
		objectives.add(new SimilarityObjective());
		objectives.add(new CostObjective());
		objectives.add(new UnselectedFeaturesObjective());
		objectives.add(new UnimportantFeaturesObjective());
		
		return objectives;
	}

	@Override
	public Instance getInstance(Path path) {
		return new TXTInstanceData(path);
	}
	
	@Override
    public List<String> getVariablesAsList(Instance instance, Solution<?> solution) {
	    
	    TXTInstanceData data = (TXTInstanceData) instance;
	    
	    NBinarySolution sol = (NBinarySolution) solution;
	    
	    BinarySet binarySet = sol.getVariableValue(0);
	    
	    List<String> variables = new ArrayList<>();
	    
        for (int i = 0; i < binarySet.getBinarySetLength(); i++) {

            if (binarySet.get(i)) {
                variables.add("Product #"+i+": "+data.getProductWithFeatures(i).toString());
            }
        }
	    
        return variables;
    }
}
