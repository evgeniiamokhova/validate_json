import java.io.IOException;
import java.util.Iterator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingMessage;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.github.fge.jsonschema.util.JsonLoader;
/**
 * Created by Evgeniia on 06.12.2018.
 */
public class JsonValidation {
    public boolean validate(String jsonData, String jsonSchema) {
        ProcessingReport report = null;
        boolean result = false;
        try {
            System.out.println("Applying schema: @<@<"+jsonSchema+">@>@ to data: #<#<"+jsonData+">#>#");
            JsonNode schemaNode = JsonLoader.fromString(jsonSchema);
            JsonNode data = JsonLoader.fromString(jsonData);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);
            report = schema.validate(data);
        } catch (JsonParseException jpex) {
            System.out.println("Error. Something went wrong trying to parse json data: #<#<"+jsonData+
                    ">#># or json schema: @<@<"+jsonSchema+">@>@. Are the double quotes included? "+jpex.getMessage());
            //jpex.printStackTrace();
        } catch (ProcessingException pex) {
            System.out.println("Error. Something went wrong trying to process json data: #<#<"+jsonData+
                    ">#># with json schema: @<@<"+jsonSchema+">@>@ "+pex.getMessage());
            //pex.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error. Something went wrong trying to read json data: #<#<"+jsonData+
                    ">#># or json schema: @<@<"+jsonSchema+">@>@");
            //e.printStackTrace();
        }
        if (report != null) {
            Iterator<ProcessingMessage> iter = report.iterator();
            while (iter.hasNext()) {
                ProcessingMessage pm = iter.next();
                System.out.println("Processing Message: "+pm.getMessage());
            }
            result = report.isSuccess();
        }
        System.out.println(" Result=" +result);
        return result;
    }

   /* public static void main(String[] args)
    {
        System.out.println( "Starting Json Validation." );
        JsonValidation app = new JsonValidation();
        String jsonData = "\"Redemption\"";
        String jsonSchema = "{ \"type\": \"string\", \"minLength\": 2, \"maxLength\": 11}";
        app.validate(jsonData, jsonSchema);
        jsonData = "Agony";  // Quotes not included
        app.validate(jsonData, jsonSchema);
        jsonData = "42";
        app.validate(jsonData, jsonSchema);
        jsonData = "\"A\"";
        app.validate(jsonData, jsonSchema);
        jsonData = "\"The pity of Bilbo may rule the fate of many.\"";
        app.validate(jsonData, jsonSchema);
    }
*/
}
