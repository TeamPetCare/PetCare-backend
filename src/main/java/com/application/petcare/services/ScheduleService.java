package com.application.petcare.services;


import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleCreateRequest request);

    ScheduleResponse updateSchedule(Integer id, ScheduleCreateRequest request);

    ScheduleResponse findScheduleById(Integer id);

    List<ScheduleResponse> findAllSchedules();

    void deleteScheduleById(Integer id);
}
