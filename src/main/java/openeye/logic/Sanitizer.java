package openeye.logic;

import com.google.common.base.Strings;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import openeye.Log;

public class Sanitizer {

	public interface ITransformer {
		public String transform(String input);
	}

	public Sanitizer() {
		this(null);
	}

	public Sanitizer(Sanitizer parent) {
		this.parent = parent;
	}

	private final Sanitizer parent;

	private static final Comparator<Integer> REVERSED = (o1, o2) -> o2 - o1;

	private static <V> Multimap<Integer, V> createPriorityList() {
		return Multimaps.newMultimap(new TreeMap<Integer, Collection<V>>(REVERSED), ArrayList::new);
	}

	private final Multimap<Integer, ITransformer> pre = createPriorityList();

	private final Multimap<Integer, ITransformer> post = createPriorityList();

	public void addPre(int priority, ITransformer transformer) {
		if (transformer != null) pre.put(priority, transformer);
	}

	public void addPost(int priority, ITransformer transformer) {
		if (transformer != null) post.put(priority, transformer);
	}

	public String sanitize(String input) {
		if (Strings.isNullOrEmpty(input)) return "";

		for (Map.Entry<Integer, Collection<ITransformer>> transformers : pre.asMap().entrySet()) {
			for (ITransformer transformer : transformers.getValue()) {
				if (Config.debugSanitizer) Log.info("%d %s", transformers.getKey(), transformer);
				input = transformer.transform(input);
			}
		}

		if (parent != null) input = parent.sanitize(input);

		for (Map.Entry<Integer, Collection<ITransformer>> transformers : post.asMap().entrySet()) {
			for (ITransformer transformer : transformers.getValue()) {
				if (Config.debugSanitizer) Log.info("%d %s", transformers.getKey(), transformer);
				input = transformer.transform(input);
			}
		}

		return input;
	}
}
