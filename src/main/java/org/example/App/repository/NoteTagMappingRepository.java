package org.example.App.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.App.entity.NoteTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NoteTagMappingRepository extends JpaRepository<NoteTagMapping, Integer> {

	List<NoteTagMapping> findByNoteId(UUID noteId);

	Optional<NoteTagMapping> findByTagIdAndNoteId(Integer tagId, UUID noteId);

}
