package be.matthieu.demoparking.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ParkedCarDto(String plate,
                           @JsonFormat(pattern = "yyyy/MM/dd - HH:mm:ss") LocalDateTime parkedSince) {
}
