package com.bluecode.chatbot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurriculumPassedElementDto {

    private Long curriculumId;
    private String curriculumName;
    private Boolean passed;
}
