package com.dev.classmoa.dto.alarm.response;

import com.dev.classmoa.domain.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAlarm {

    private Long id;

    public AddAlarm (Alarm alarm) {
        this.id = alarm.getId();
    }
}