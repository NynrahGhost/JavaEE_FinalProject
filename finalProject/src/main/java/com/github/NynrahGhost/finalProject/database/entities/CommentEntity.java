package com.github.NynrahGhost.finalProject.database.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Recipes")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentEntity {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@ManyToOne(optional=false)
	@JoinColumn(name="author")
    private UserEntity author;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="recipe")
    private RecipeEntity recipe;
	
	@Column(name = "text")
    private String text;
	
    @Column(name = "date")
    private Date date;

}