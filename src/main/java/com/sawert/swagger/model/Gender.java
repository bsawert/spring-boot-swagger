package com.sawert.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Gender
 */
public enum Gender {
  
  MALE("Male"),
  
  FEMALE("Female"),
  
  NEUTERED("Neutered"),
  
  OTHER("Other"),
  
  UNKNOWN("Unknown");

  private String value;

  Gender(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Gender fromValue(String text) {
    for (Gender b : Gender.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

