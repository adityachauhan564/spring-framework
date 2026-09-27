package com.jbdl63.digitallibrary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jbdl63.digitallibrary.dto.UpdateAuthorDto;
import com.jbdl63.digitallibrary.exception.BadRequestException;
import com.jbdl63.digitallibrary.exception.DataNotFoundException;
import com.jbdl63.digitallibrary.model.Author;
import com.jbdl63.digitallibrary.repository.AuthorRepository;
import com.jbdl63.digitallibrary.service.AuthorService;

/*
 * A plain unit test: no Spring, no database. The repository is a Mockito mock, so each test decides
 * what it returns (when...thenReturn) and checks how the service used it (verify / ArgumentCaptor).
 */
@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks                      // builds AuthorService, passing the mock to its constructor
    private AuthorService authorService;

    @Test
    void addNewAuthorIgnoresAnyIdTheClientSent() {
        Author author = Author.builder().authorId(1111).authorName("pranav").authorAddress("nashik").build();
        when(authorRepository.save(any())).thenAnswer(call -> call.getArgument(0));

        Author saved = authorService.addNewAuthor(author);

        assertThat(saved.getAuthorId()).isNull();     // the database will generate it
    }

    @Test
    void uploadParsesEveryLineAfterTheHeader() {
        authorService.uploadAuthorsDataToDatabase("""
                authorName, authorAddress
                ABC, Kolkata
                PQR, Jaipur

                Divya, Nagpur""");

        assertThat(savedAuthors()).extracting(Author::getAuthorName).containsExactly("ABC", "PQR", "Divya");
    }

    @Test
    void uploadHandlesWindowsLineEndings() {
        authorService.uploadAuthorsDataToDatabase("authorName,authorAddress\r\nABC,Kolkata\r\n");

        assertThat(savedAuthors().get(0).getAuthorAddress()).isEqualTo("Kolkata");   // not "Kolkata\r"
    }

    @Test
    void uploadRejectsALineWithTheWrongColumnCount() {
        BadRequestException e = assertThrows(BadRequestException.class,
                () -> authorService.uploadAuthorsDataToDatabase("authorName,authorAddress\nonly-a-name\n"));
        assertThat(e.getMessage()).contains("Line 2");
    }

    @Test
    void updateChangesOnlyTheAddress() {
        Author author = Author.builder().authorId(7).authorName("pranav").authorAddress("nashik").build();
        when(authorRepository.findById(7)).thenReturn(Optional.of(author));

        Author updated = authorService.updateAuthorAddress(new UpdateAuthorDto(7, "Mumbai"));

        assertThat(updated.getAuthorAddress()).isEqualTo("Mumbai");
        assertThat(updated.getAuthorName()).isEqualTo("pranav");
    }

    @Test
    void updatingAMissingAuthorIsNotFound() {
        when(authorRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> authorService.updateAuthorAddress(new UpdateAuthorDto(2, "abc")));
    }

    @SuppressWarnings("unchecked")
    private List<Author> savedAuthors() {
        ArgumentCaptor<List<Author>> captor = ArgumentCaptor.forClass(List.class);
        verify(authorRepository).saveAll(captor.capture());
        return captor.getValue();
    }
}
