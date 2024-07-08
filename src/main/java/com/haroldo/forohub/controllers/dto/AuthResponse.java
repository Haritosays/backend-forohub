package com.haroldo.forohub.controllers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"token", "type"})
public record AuthResponse(
                           String token,
                           String type) {
}
