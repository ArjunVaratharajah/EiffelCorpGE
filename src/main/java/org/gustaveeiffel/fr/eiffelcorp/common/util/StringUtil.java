package org.gustaveeiffel.fr.eiffelcorp.common.util;

import java.util.List;

public class StringUtil {

	public static String join(List<String> list) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String item : list) {
			if (first) {
				first = false;
			}
			sb.append(item);
		}
		return sb.toString();
	}

}
