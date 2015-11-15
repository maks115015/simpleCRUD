package com.spr.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
	public static final int START_SEQ = 1000;

	@Id
	@SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
	private Integer id;

	@NotNull
	@Size(min=2, max=30)
	@Column(name = "name")
	private String name;

	@NotNull
	@Digits(integer=3, fraction=0)
	@Column(name = "age")
	private Integer age;

	@Column(name = "isAdmin")
	private boolean isAdmin;

	@Column(name = "createdDate", updatable = false, insertable = false)
	private Timestamp date;



	public User(Integer id,String name, Integer age, boolean isAdmin) {
        this.id=id;
		this.name = name;
		this.age = age;
		this.isAdmin = isAdmin;
	}

	public User(String name, Integer age, boolean isAdmin) {
		this.name = name;
		this.age = age;
		this.isAdmin = isAdmin;
	}

	public User() {
    }
    public Timestamp getDate() {
		return date;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", isAdmin=" + isAdmin +
				'}';
	}
}
