import java.io.*;
import java.util.*;

public class DataSet {
    public final static String[] VARIABLE_NAMES = {"buying", "maint", "doors", "persons", "lug_boot", "safety", "car"};

    final List<Map<String, String>> instances;
    final Map<String, Set<String>> values;

    /**
     * A data set has a hardcoded list of variable names, a list of instances, and
     * a map from variables to possible values for each variable.
     *
     * Each instance is a map from variable names to values.
     */
    private DataSet(List<Map<String, String>> instances, Map<String, Set<String>> values) {
        this.instances = instances;
        this.values = values;
    }

    public static DataSet fromFile(File inputFile) throws IOException {
        List<Map<String, String>> instances = new ArrayList<Map<String, String>>();
        Map<String, Set<String>> allValues = new HashMap<String, Set<String>>();
        for (String variableName : VARIABLE_NAMES) {
            allValues.put(variableName, new HashSet<String>());
        }

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            assert values.length == VARIABLE_NAMES.length;
            Map<String, String> instance = new HashMap<String, String>();
            for (int i = 0; i < values.length; i++) {
                instance.put(VARIABLE_NAMES[i], values[i]);
                allValues.get(VARIABLE_NAMES[i]).add(values[i]);
            }
            instances.add(instance);
        }

        return new DataSet(instances, allValues);
    }

    /**
     * @return a DataSet containing only instances that are consistent with the query
     */
    DataSet conditionalSubset(Query query) {
        List<Map<String, String>> newInstances = new ArrayList<Map<String, String>>();

        for (Map<String, String> instance : instances) {
            boolean isConsistent = true;
            for (Map.Entry<String, Set<String>> condition: query.conditions.entrySet()) {
                String variable = condition.getKey();
                Set<String> values = condition.getValue();
                if (!values.contains(instance.get(variable))) {
                    isConsistent = false;
                    break;
                }
            }
            if (isConsistent) {
                newInstances.add(instance);
            }
        }

        return new DataSet(newInstances, values);
    }
}
