package com.dev.classmoa.dto.alarm.request;

import com.dev.classmoa.domain.entity.Alarm;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelAlarm {

    private Long id;

    public Alarm toEntity() {
        return Alarm.canceler()
                .id(id)
                .cancel();
    }
}