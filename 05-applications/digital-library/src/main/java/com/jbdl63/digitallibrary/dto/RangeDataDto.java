package com.jbdl63.digitallibrary.dto;

import java.util.List;

import com.jbdl63.digitallibrary.model.Author;

public record RangeDataDto(int dataSize, List<Author> data) {
}
