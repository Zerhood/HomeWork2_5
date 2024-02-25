package skypro.dto;

import lombok.Data;

@Data
public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;
}