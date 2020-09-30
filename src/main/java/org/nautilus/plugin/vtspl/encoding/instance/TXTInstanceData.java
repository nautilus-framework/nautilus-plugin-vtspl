package org.nautilus.plugin.vtspl.encoding.instance;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nautilus.core.gui.Tab;
import org.nautilus.core.gui.TableTabContent;
import org.nautilus.core.model.Instance;
import org.nautilus.core.util.InstanceReader;
import org.nautilus.core.util.SimilarityUtils;

public class TXTInstanceData extends Instance implements AbstractTXTInstanceData {
	
	protected int numberOfProducts;
	
	protected int numberOfFeatures;
	
	protected int numberOfMutants;
	
	protected int numberOfPairs;
	
	protected List<String> features;
	
	protected List<String> mandatoryFeatures;
	
	protected List<String> optionalFeatures;
	
	protected List<List<String>> productWithOptionalFeatures;
	
	protected List<List<Integer>> products;
	
	protected List<List<Integer>> mutants;
	
	protected List<List<Integer>> pairs;
	
	protected List<Double> costs;
	
	protected List<Double> importances;
	
	protected List<Double> productCosts;
	
	protected List<Double> productImportances;
	
	protected double sumOfCosts;
	
	protected double sumOfImportances;
	
	protected double[][] similarity;
	
	protected Map<String, Integer> indexesForOptionalFeatures;
	
	public TXTInstanceData(Path path) {

		this.products = new ArrayList<>();
		this.mutants = new ArrayList<>();
		this.pairs = new ArrayList<>();
		this.productCosts = new ArrayList<>();
		this.productImportances = new ArrayList<>();
		this.productWithOptionalFeatures = new ArrayList<>();
		this.indexesForOptionalFeatures = new HashMap<>();
		
		InstanceReader reader = new InstanceReader(path, " ");
		
		reader.ignoreLine();
		this.numberOfProducts = reader.readIntegerValue();
		
		reader.ignoreLine();
		this.numberOfFeatures = reader.readIntegerValue();
		
		reader.ignoreLine();
		this.numberOfMutants = reader.readIntegerValue();
		
		reader.ignoreLine();
		this.numberOfPairs = reader.readIntegerValue();
		
		reader.ignoreLine();
		this.features = reader.readStringValues();
		
		reader.ignoreLine();
		this.mandatoryFeatures = reader.readStringValues();
		
		reader.ignoreLine();
		for (int i = 0; i < getNumberOfProducts(); i++) {
			products.add(reader.readIntegerValues());
		}
		
		reader.ignoreLine();
		for (int i = 0; i < getNumberOfProducts(); i++) {
			this.mutants.add(reader.readIntegerValues());
		}
		
		reader.ignoreLine();
		for (int i = 0; i < getNumberOfProducts(); i++) {
			this.pairs.add(reader.readIntegerValues());
		}
		
		reader.ignoreLine();
		this.costs = reader.readDoubleValues();
		
		reader.ignoreLine();
		this.importances = reader.readDoubleValues();
		
		// Calculate some data because we want performance

		for (int i = 0; i < getNumberOfProducts(); i++) {

			productCosts.add(calculateProductCost(i));
			productImportances.add(calculateProductImportance(i));

			this.sumOfCosts += productCosts.get(i);
			this.sumOfImportances += productImportances.get(i);
			
			this.productWithOptionalFeatures.add(calculateProductWithOptionalFeatures(i));
		}
		
//		this.similarity = calculateSimilarity(numberOfProducts);
		
		this.optionalFeatures = calculateOptionalFeatures();

		for (int i = 0; i < optionalFeatures.size(); i++) {
			this.indexesForOptionalFeatures.put(optionalFeatures.get(i), i);
		}
	}
	
	@Override
	public Map<String, Integer> getIndexesForOptionalFeatures() {
		return indexesForOptionalFeatures;
	}
	
	@Override
	public int getNumberOfMutants() {
		return this.numberOfMutants;
	}

	@Override
	public int getNumberOfProducts() {
		return this.numberOfProducts;
	}

	@Override
	public int getNumberOfFeatures() {
		return this.numberOfFeatures;
	}

	@Override
	public int getNumberOfPairs() {
		return this.numberOfPairs;
	}

	@Override
	public List<String> getFeatures() {
		return this.features;
	}	
	
	@Override
	public List<String> getOptionalFeatures() {
		return this.optionalFeatures;
	}
	
	@Override
	public List<Integer> getProduct(int index) {
		return products.get(index);
	}
	
	public List<String> getProductWithFeatures(int index) {

		List<String> features = new ArrayList<>();

		for (Integer featureId : getProduct(index)) {
			features.add(getFeatures().get(featureId));
		}

		return features;
	}
	
	public List<String> getProductWithoutMandatoryFeatures(int index) {
		return this.productWithOptionalFeatures.get(index);
	}
	
	public List<String> calculateProductWithOptionalFeatures(int index) {

		List<String> features = getProductWithFeatures(index);

		for (String mandatory : mandatoryFeatures) {
			features.remove(mandatory);
		}

		return features;
	}
	
	protected List<String> calculateOptionalFeatures() {

		List<String> features = new ArrayList<>();

		for (String feature : getFeatures()) {

			if (!mandatoryFeatures.contains(feature)) {
				features.add(feature);
			}
		}

		return features;
	}
	
	protected double calculateProductCost(int index) {

		double sum = 0.0;

		for (Integer featureId : getProduct(index)) {
			sum += costs.get(featureId);
		}

		return sum;
	}
	
	protected double calculateProductImportance(int index) {

		double sum = 0.0;

		for (Integer featureId : getProduct(index)) {
			sum += importances.get(featureId);
		}

		return sum;
	}
	
	protected double[][] calculateSimilarity(int size) {

		double[][] similarity = new double[size][size];

		for (int i = 0; i < size; i++) {

			List<Integer> features1 = this.getProduct(i);

			for (int j = i; j < size; j++) {

				if (i != j) {

					List<Integer> features2 = this.getProduct(j);

					similarity[i][j] = SimilarityUtils.getJaccardIndexFromInteger(features1, features2);
					similarity[j][i] = similarity[i][j];
				}
			}
		}

		return similarity;
	}

	@Override
	public double getProductCost(int index) {
		return this.productCosts.get(index);
	}

	@Override
	public double getSumOfCosts() {
		return this.sumOfCosts;
	}

	@Override
	public double getProductImportance(int index) {
		return this.productImportances.get(index);
	}

	@Override
	public double getSumOfImportance() {
		return this.sumOfImportances;
	}
	
	@Override
	public double getSimilarity(int i, int j) {
		
//		require(i).isInteger().between(0, getNumberOfProducts() - 1);
//		require(j).isInteger().between(0, getNumberOfProducts() - 1);

		return this.similarity[i][j];
	}

	@Override
	public List<Integer> getMutants(int index) {
		return this.mutants.get(index);
	}

	@Override
	public List<Integer> getPairs(int index) {
		return this.pairs.get(index);
	}

	public List<Double> getCosts() {
		return costs;
	}

	public void setCosts(List<Double> costs) {
		this.costs = costs;
	}

	public List<Double> getImportances() {
		return importances;
	}

	public void setImportances(List<Double> importances) {
		this.importances = importances;
	}
	
	@Override
    public List<Tab> getTabs(Instance data) {

        TXTInstanceData c = (TXTInstanceData) data;

        List<Tab> tabs = new ArrayList<>();

        tabs.add(getFeaturesTab(c));
        tabs.add(getProductsTab(c));
        tabs.add(getPairwiseCoverageTab(c));
        tabs.add(getMutationCoverageTab(c));

        return tabs;
    }

    protected Tab getFeaturesTab(TXTInstanceData data) {

        TableTabContent table = new TableTabContent(Arrays.asList("Feature", "Cost", "Importance"));

        List<String> features = data.getFeatures();

        for (int i = 0; i < data.getNumberOfFeatures(); i++) {
            table.getRows().add(Arrays.asList(
                    features.get(i),
                    "" + data.getCosts().get(i),
                    "" + data.getImportances().get(i)
            ));
        }

        return new Tab("Features", table);
    }

    protected Tab getProductsTab(TXTInstanceData data) {

        TableTabContent table = new TableTabContent(Arrays.asList("Product Id", "Feature", "Cost", "Importance"));

        for (int i = 0; i < data.getNumberOfProducts(); i++) {
            table.getRows().add(Arrays.asList("Product " + i, 
                data.getProduct(i).toString(), 
                "" + data.getProductCost(i),
                "" + data.getProductImportance(i)
            ));
        }

        return new Tab("Products", table);
    }
    
    protected Tab getPairwiseCoverageTab(TXTInstanceData data) {

        TableTabContent table = new TableTabContent(Arrays.asList("Product Id", "Covered", "Uncovered", "# of Uncovered"));

        for (int i = 0; i < data.getNumberOfProducts(); i++) {

            List<String> covered = new ArrayList<>();
            List<String> unCovered = new ArrayList<>();

            for (int j = 0; j < data.getNumberOfPairs(); j++) {
                if (data.getPairs(i).contains(j)) {
                    covered.add(String.valueOf(j));
                } else {
                    unCovered.add(String.valueOf(j));
                }
            }

            table.getRows().add(Arrays.asList("Product " + i, covered.toString(), unCovered.toString(), ""+unCovered.size()));
        }

        return new Tab("Pairwise Coverate", table);
    }
    
    protected Tab getMutationCoverageTab(TXTInstanceData data) {

        TableTabContent table = new TableTabContent(Arrays.asList("Product Id", "Dead", "Alive", "# of Alive"));

        for (int i = 0; i < data.getNumberOfProducts(); i++) {

            List<String> covered = new ArrayList<>();
            List<String> unCovered = new ArrayList<>();

            for (int j = 0; j < data.getNumberOfMutants(); j++) {
                if (data.getMutants(i).contains(j)) {
                    covered.add(String.valueOf(j));
                } else {
                    unCovered.add(String.valueOf(j));
                }
            }

            table.getRows().add(Arrays.asList("Product " + i, covered.toString(), unCovered.toString(), ""+unCovered.size()));
        }

        return new Tab("Mutation Coverage", table);
    }
}
