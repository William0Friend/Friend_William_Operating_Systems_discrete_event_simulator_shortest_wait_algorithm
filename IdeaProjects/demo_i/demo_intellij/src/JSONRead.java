//Thanks to https://www.educba.com/json-in-java/
//importing JSON simple libraries
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import com.github.cliftonlabs.json_simple.*;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
public class JSONRead
{
public static void main(String[] args) throws Exception
{
// The file JSON.json is parsed
Object objc = new JSONParser().parse(new FileReader("JSON.json"));
// objc is convereted to JSON object
JSONObject job = (JSONObject) objc;
// obtaining the fname and lname
String fName = (String) job.get("fName");
String lName = (String) job.get("lName");
System.out.println(fName);
System.out.println(lName);
// age is obtained
long age1 = (long) job.get("age1");
System.out.println(age1);
// address is obtained
Map address1 = ((Map)job.get("address1"));
// iterating through the address
Iterator<Map.Entry> itr = address.entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pair1 = itr1.next();
System.out.println(pair1.getKey() + " : " + pair1.getValue());
}
// phone numbers are obtained
JSONArray jab = (JSONArray) job.get("phoneNos");
// iterating phoneNumbers
Iterator itr1 = jab.iterator();
while (itr1.hasNext())
{
itr = ((Map) itr1.next()).entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pair1 = itr.next();
System.out.println(pair1.getKey() + " : " + pair1.getValue());
}
}
}
}