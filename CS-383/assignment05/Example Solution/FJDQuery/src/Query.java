import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Query {
    final String[] queryVariables;
    final Map<String, Set<String>> conditions;

    /**
     * a query is represented by a list of query variables, and a set of conditions; the
     * conditions are each a map from variable name to a set of permissible values
     */
    Query(String[] queryVariables, Map<String, Set<String>> conditions) {
        this.queryVariables = queryVariables;
        this.conditions = conditions;
    }

    public static Query fromFile(File inputFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = br.readLine();
        String[] queryVariables = line.split("\\s+");

        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();

        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split("\\s+");
            String variable = splitLine[0];
            Set<String> values = new HashSet<String>();
            for (int i = 1; i < splitLine.length; i++) {
                values.add(splitLine[i]);
            }
            conditions.put(variable, values);
        }

        return new Query(queryVariables, conditions);
    }

    /**
     * @return a list of sub-queries that correspond to each distinct possible setting of the query variables
     */
    List<Query> enumerateQueryValues(Map<String, Set<String>> values) {
        List<List<String>> valuesList = valueList(queryVariables, values);
        List<List<String>> valuesProduct = cartesianProduct(valuesList);

        List<Query> result = new ArrayList<Query>();
        for (List<String> variableValues : valuesProduct) {
            Map<String, Set<String>> condition = new HashMap<String, Set<String>>();
            for (int i = 0; i < queryVariables.length; i++) {
                Set<String> conditionValue = new HashSet<String>();
                conditionValue.add(variableValues.get(i));
                condition.put(queryVariables[i], conditionValue);
            }
            result.add(new Query(queryVariables, condition));
        }
        return result;
    }

    /**
     * @return a list of sublists, one per query variable; each sublist is the list of all possible
     * values associated with each query variable
     */
    List<List<String>> valueList(String[] queryVariables, Map<String, Set<String>> values) {
        List<List<String>> result = new ArrayList<List<String>>();
        for (String variable: queryVariables) {
            result.add(new ArrayList<String>(values.get(variable)));
        }
        return result;
    }

    /**
     * Recursively compute the cartesian product of several lists of values. For example, given
     *
     * [[a, b, c], [d, e]]
     *
     * return
     *
     * [[a, d], [a, e], [b, d], [b, e], [c, d], [c, e]]
     *
     * @param valuesList: a list of sublists; each sublist is a set of values
     * @return the cartesian product of the sublists
     */
    static List<List<String>> cartesianProduct(List<List<String>> valuesList) {
        if (valuesList.size() == 1) {
            List<List<String>> result = new ArrayList<List<String>>();
            for (String value: valuesList.get(0)) {
                List<String> newElement = new ArrayList<String>();
                newElement.add(value);
                result.add(newElement);
            }
            return result;
        }
        else {
            List<String> currentValues = valuesList.get(0);
            List<List<String>> sublist = new ArrayList<List<String>>(valuesList);
            sublist.remove(0);
            List<List<String>> subproduct = cartesianProduct(sublist);

            List<List<String>> result = new ArrayList<List<String>>();
            for (String value: currentValues) {
                for (List<String> element: subproduct) {
                    List<String> newElement = new ArrayList<String>(element);
                    newElement.add(0, value);
                    result.add(newElement);
                }
            }
            return result;
        }
    }
}
