package org.university.diplom.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.university.diplom.constants.FunctionType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "function_type")
    private FunctionType functionType;

    @Column(name = "image_url")
    private String imageUrl;

    public Image(String imageUrl, FunctionType functionType) {
        this.imageUrl = imageUrl;
        this.functionType = functionType;
    }
}