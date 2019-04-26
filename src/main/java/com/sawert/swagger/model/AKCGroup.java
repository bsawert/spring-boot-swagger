package com.sawert.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets AKCGroup
 */
public enum AKCGroup {
  
  HOUND("Hound"),
  
  TERRIER("Terrier"),
  
  WORKING("Working"),
  
  HERDING("Herding"),
  
  SPORTING("Sporting"),
  
  NON_SPORTING("Non-Sporting"),
  
  TOY("Toy"),
  
  MISCELLANEOUS("Miscellaneous");

  private String value;

  AKCGroup(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AKCGroup fromValue(String text) {
    for (AKCGroup b : AKCGroup.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

