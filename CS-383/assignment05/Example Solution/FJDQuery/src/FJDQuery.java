import java.io.File;
import java.io.IOException;

public class FJDQuery {

    public static void main(String[] args) throws IOException {
        DataSet data = DataSet.fromFile(new File("car.data"));
        Query query = Query.fromFile(new File(args[0]));
        DataSet conditionalData = data.conditionalSubset(query);
        double conditionalCount = conditionalData.instances.size();
        
        for (Query queryVariableSetting: query.enumerateQueryValues(data.values)) {

            for (String variableName: query.queryVariables) {
                System.out.print(queryVariableSetting.conditions.get(variableName).iterator().next() + " ");
            }
            DataSet matchingData = conditionalData.conditionalSubset(queryVariableSetting);
            double matchingCount = matchingData.instances.size();
            System.out.println(matchingCount / conditionalCount);
        }

    }

}
