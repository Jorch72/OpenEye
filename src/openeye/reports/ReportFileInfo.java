package openeye.reports;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.SerializedName;

import cpw.mods.fml.common.versioning.ArtifactVersion;

/* I'm marking all fields with serializable, just to prevent screwups. Yeah, I know about FieldNamingPolicy */
public class ReportFileInfo {

	public static class SerializableTweak {
		@SerializedName("plugin")
		public String plugin;

		@SerializedName("class")
		public String cls;
	}

	public static class SerializableMod {
		@SerializedName("modId")
		public String modId;

		@SerializedName("name")
		public String name;

		@SerializedName("version")
		public String version;

		@SerializedName("description")
		public String description;

		@SerializedName("url")
		public String url;

		@SerializedName("updateUrl")
		public String updateUrl;

		@SerializedName("credits")
		public String credits;

		@SerializedName("parent")
		public String parent;

		@SerializedName("authors")
		public Collection<String> authors = ImmutableList.of();

		@SerializedName("requiredMods")
		public Collection<ArtifactVersion> requiredMods = ImmutableList.of();

		@SerializedName("dependants")
		public Collection<ArtifactVersion> dependants = ImmutableList.of();

		@SerializedName("dependencies")
		public Collection<ArtifactVersion> dependencies = ImmutableList.of();
	}

	@SerializedName("signature")
	public String signature;

	@SerializedName("filename")
	public String filename;

	@SerializedName("size")
	public Long size;

	@SerializedName("mods")
	public List<SerializableMod> mods = ImmutableList.of();

	@SerializedName("tweakers")
	public List<SerializableTweak> tweakers = ImmutableList.of();

	@SerializedName("classTransformers")
	public List<String> classTransformers = ImmutableList.of();

	@SerializedName("packages")
	public List<String> packages = ImmutableList.of();
}
