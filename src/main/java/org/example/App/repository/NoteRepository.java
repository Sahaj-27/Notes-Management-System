package org.example.App.repository;

import java.util.UUID;

import org.example.App.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

}
