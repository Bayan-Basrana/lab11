package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class Category {



    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @NotEmpty(message = "name is empty")
    @Size(min=5,message = "name must be more than 4")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String name ;

    public Category() {

    }
    public Category(Integer categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }





    public Integer getCategoryId() {
        return categoryId;
    }

    public @NotEmpty(message = "name is empty") @Size(min = 5, message = "name must be more than 4") String getName() {
        return name;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(@NotEmpty(message = "name is empty") @Size(min = 5, message = "name must be more than 4") String name) {
        this.name = name;
    }
}
