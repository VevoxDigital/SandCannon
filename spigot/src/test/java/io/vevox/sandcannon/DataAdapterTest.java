package io.vevox.sandcannon;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("unused WeakerAccess")
public class DataAdapterTest {

  @Test
  public void adaptsToJsonObject() {
    assertTrue("should be JsonObject", DataAdapter.toJson(new JSONObject()) instanceof JsonObject);

    String str = "foo";
    JsonElement e = DataAdapter.toJson(str);
    assertTrue("should be a JsonPrimitive", e instanceof JsonPrimitive);
    assertTrue("should equal input string", e.getAsString().equals(str));
  }

  @Test
  public void adaptsArrayToJson() {
    Object[] objects = new Object[]{ new JSONObject(), "foo" };
    JsonElement[] elements = DataAdapter.toJson(objects);
    assertTrue("should be a JsonObject", elements[0] instanceof JsonObject);
    assertTrue("should be a JsonPrimitive", elements[1] instanceof JsonPrimitive);
    assertTrue("should equal input string", elements[1].getAsString().equals(objects[1]));
  }

  @Test
  public void adaptsFromJson() {
    assertTrue("should be JSONObject", DataAdapter.fromJson(new JsonObject()) instanceof JSONObject);

    JsonPrimitive prim = new JsonPrimitive("foo");
    Object e = DataAdapter.fromJson(prim);
    assertTrue("should be String", e instanceof String);
    assertTrue("should equal input primitive", e.equals(prim.getAsString()));
  }

  @Test
  public void adaptsArrayFromJson() {
    JsonElement[] elements = new JsonElement[]{ new JsonObject(), new JsonPrimitive("foo") };
    Object[] objects = DataAdapter.fromJson(elements);
    assertTrue("should be JSONObject", objects[0] instanceof JSONObject);
    assertTrue("shoudl be String", objects[1] instanceof String);
    assertTrue("should equal input primitive", objects[1].equals(elements[1].getAsString()));
  }

}