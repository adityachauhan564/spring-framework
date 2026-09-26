package com.jbdl63.digitalLibrary.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbdl63.digitalLibrary.Exceptions.BadRequestException;
import com.jbdl63.digitalLibrary.Exceptions.DataNotFoundException;
import com.jbdl63.digitalLibrary.Model.Author;
import com.jbdl63.digitalLibrary.Repository.AuthorRepository;
import com.jbdl63.digitalLibrary.dto.UpdateAuthorDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author addNewAuthor(Author author) throws Exception{
        return authorRepository.save(author);
    }

    public Author fetchAuthorDetailsByName(String authorName) {
        Author byAuthorName = authorRepository.findByAuthorName(authorName);
        if (byAuthorName == null) {
            throw new DataNotFoundException("Author not found: " + authorName);
        }
        return byAuthorName;
    }

    public List<Author> fetchAllAvailableAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthorAddress(UpdateAuthorDto updateAuthorDto) {
        try {
            Author author = authorRepository.findById(updateAuthorDto.getAuthorId()).orElseThrow(
                    () -> new DataNotFoundException("Author not exist")
            );
            author.setAuthorAddress(updateAuthorDto.getAddress());
            return authorRepository.save(author);
        }
        catch(DataNotFoundException e) {
            throw e; // keep it a 404, not a 400
        }
        catch(RuntimeException e) {
            log.error("Error is occurred while working with update operations with exception : {}", e.getMessage());
            throw new BadRequestException("Update Operation is Failed due to Exception :" + e.getMessage());
        }
    }

    //For New Entry - Persist
    //For Update Entry - Merge

    public void deleteById(Integer authorId) {
        authorRepository.deleteById(authorId);
    }

    public void uploadAuthorsDataToDatabase(String fileContent) {
        List<String> authorsData = List.of(fileContent.split("\\r?\\n")); // handles Windows line endings
        List<Author> authors = new ArrayList<>();
        for(int i = 1; i < authorsData.size(); i++) {
            if (authorsData.get(i).isBlank()) continue;
            String[] row = authorsData.get(i).split(",");
            if (row.length < 3) {
                throw new BadRequestException("Line " + (i + 1) + " needs 3 columns: authorId,authorName,authorAddress");
            }
            authors.add(Author.builder()
                            .authorId(Integer.valueOf(row[0].trim()))
                            .authorName(row[1].trim())
                            .authorAddress(row[2].trim())
                    .build());
        }
        authorRepository.saveAll(authors);
    }
}