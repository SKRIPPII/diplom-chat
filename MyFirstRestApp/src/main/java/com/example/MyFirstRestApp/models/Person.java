package com.example.MyFirstRestApp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "People")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 2,max = 50,message = "Имя должно содержать минимум 2 символа!")
    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым!")
    private  String name;
    @Column(name = "age")
    @Min(value = 18,message = "Возраст должен быть 18+")
    private int age;
    @Column(name = "email")
    @Email(message = "Введите корректный Email!")
    private String email;
    @Column(name = "password")
    @Size(min = 8,message = "Пароль должен содержать минимум 8 символов!")
    @NotEmpty
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "status")
    private String status;

    public Person() {
    }

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password  +
                '}';
    }
}
