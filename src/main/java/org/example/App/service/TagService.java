package org.example.App.service;

import java.util.List;
import java.util.stream.Collectors;

import org.example.App.constant.ContextType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.example.App.dto.response.TagRetreivalRequestDto;
import org.example.App.entity.Tag;
import org.example.App.repository.NoteTagMappingRepository;
import org.example.App.repository.TagRepository;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagService {

	private final TagRepository tagRepository;

	private final NoteTagMappingRepository noteTagMappingRepository;


	public List<Tag> retreiveAll() {
		return tagRepository.findAll();
	}

	public ResponseEntity<List<String>> retreive(final TagRetreivalRequestDto tagRetreivalRequest, final String token) {
		if (tagRetreivalRequest.getContextType().equalsIgnoreCase(ContextType.NOTE.getName())) {
			return ResponseEntity
					.ok(noteTagMappingRepository.findByNoteId(tagRetreivalRequest.getContextId()).parallelStream()
							.map(noteTagMapping -> noteTagMapping.getTag().getName()).collect(Collectors.toList()));
		}

		return ResponseEntity.badRequest().build();

	}

}
