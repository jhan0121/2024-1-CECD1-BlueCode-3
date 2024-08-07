package com.bluecode.chatbot.repository;

import com.bluecode.chatbot.domain.Curriculums;

import java.util.List;
import java.util.Optional;

public interface CurriculumRepositoryCustom {

    // chapNum = 0인 루트 커리큘럼 검색
    List<Curriculums> findAllRootCurriculumList();

    // 챕터 순번(chapterNum) & root 커리큘럼 id 기반으로 커리큘럼 검색
    Optional<Curriculums> findByRootIdAndChapterNum(Long rootCurriculumId, int chapterNum);
}
