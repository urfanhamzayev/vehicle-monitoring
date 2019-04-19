package com.urfan.customerservice.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated customer ID.")
    @Column(name = "ID")
    private Long id;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    @ApiModelProperty(notes = "The Customer name.")
    @Size(min = 3, max = 150)
    private String name;

    @NotNull
    @Size(max = 150)
    @ApiModelProperty(notes = "The Address Line.")
    @Column(name = "ADDRESS_LINE", nullable = false)
    private String addressLine;

    @NotNull
    @Size(max = 50)
    @Column(name = "CITY", nullable = false)
    @ApiModelProperty(notes = "The Address City.")
    private String city;

    @NotNull
    @Size(max = 50)
    @Column(name = "STATE", nullable = false)
    @ApiModelProperty(notes = "The Address State.")
    private String state;

    @NotNull
    @Size(max = 50)
    @Column(name = "COUNTRY", nullable = false)
    @ApiModelProperty(notes = "The Address Country.")
    private String country;

    @NotNull
    @Size(max = 6)
    @Column(name = "POSTAL_CODE", nullable = false)
    @ApiModelProperty(notes = "The Address Postal Code.")
    private String postalCode;

    public Customer() {
    }

    public Customer(String name, String addressLine, String city, String state, String country, String postalCode) {
        this.name = name;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
