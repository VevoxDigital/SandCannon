package io.vevox.sandcannon;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * An adapter class to adapt the data from <code>socket.io-java</code> to a more use-able format.
 *
 * @author Matthew Struble
 */
@SuppressWarnings("unused WeakerAccess")
public class DataAdapter {

  /**
   * Convert an array of objects into {@link JsonElement JsonElements}.
   *
   * @param objects The objects to convert.
   *
   * @return The array of elements.
   * @throws JsonParseException If an object is not valid JSON.
   * @throws AssertionError     If the objects array is null.
   */
  public static JsonElement[] toJson(Object... objects) throws JsonParseException, AssertionError {
    assert objects != null;
    return Arrays.stream(objects)
        .map(DataAdapter::toJson)
        .collect(Collectors.toList())
        .toArray(new JsonElement[objects.length]);
  }

  /**
   * Converts an object into a {@link JsonElement}.
   * @param object The object to convert.
   * @return The element.
   * @throws JsonParseException If the object is not valid for JSON.
   */
  @SuppressWarnings("deprecation")
  public static JsonElement toJson(Object object) throws JsonParseException {
    return new JsonParser().parse(object == null ? "null" : object.toString());
  }

  /**
   * Converts an array of {@link JsonElement JsonElements} back into objects.
   * @param elements The elements to convert.
   * @return The objects.
   * @throws AssertionError If the elements array is null.
   */
  public static Object[] fromJson(JsonElement... elements) throws AssertionError {
    assert elements != null;
    return Arrays.stream(elements)
        .map(DataAdapter::fromJson)
        .collect(Collectors.toList())
        .toArray();
  }

  /**
   * Converts a {@link JsonElement} back into an object.
   * @param element The element to convert.
   * @return The object, or <code>null</code> if the conversion failed.
   */
  public static Object fromJson(JsonElement element) {
    try {
      return new JSONTokener(element == null ? "null" : element.toString()).nextValue();
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }

}
