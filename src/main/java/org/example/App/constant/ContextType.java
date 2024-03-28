package org.example.App.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContextType {

	NOTE(1, "Notes");

	private final Integer id;
	private final String name;

}
