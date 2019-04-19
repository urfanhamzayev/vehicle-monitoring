package com.alten.vehiclesignalgenerator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(as = Vehicle.class)
public class Page {
     private Vehicle[] content;

     public Vehicle[] getContent() {
          return content;
     }

     public void setContent(Vehicle[] content) {
          this.content = content;
     }
}
