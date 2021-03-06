package openeye.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import java.lang.reflect.Type;
import openeye.struct.TypedCollections;
import openeye.struct.TypedCollections.ReportsList;
import openeye.struct.TypedCollections.ResponseList;

public class GsonUtils {

	public static final JsonSerializer<ArtifactVersion> VERSION_SERIALIZER = new JsonSerializer<ArtifactVersion>() {
		@Override
		public JsonElement serialize(ArtifactVersion src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject obj = new JsonObject();
			obj.addProperty("label", src.getLabel());
			obj.addProperty("version", src.getRangeString());
			return obj;
		}
	};

	public static GsonBuilder setupCommonBuilder() {
		return new GsonBuilder()
				.registerTypeAdapter(ReportsList.class, TypedCollections.REPORT_LIST_CONVERTER)
				.registerTypeAdapter(ResponseList.class, TypedCollections.RESPONSE_LIST_CONVERTER)
				.registerTypeAdapter(ArtifactVersion.class, VERSION_SERIALIZER);
	}

	public static final Gson NET_GSON = setupCommonBuilder().create();

	public static final Gson PRETTY_GSON = setupCommonBuilder().setPrettyPrinting().create();

}
