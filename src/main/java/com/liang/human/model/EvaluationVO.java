package com.liang.human.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluationVO {
    private long contentId;
    private String content;
    private String contentUser;
    private int contentRole;
}
