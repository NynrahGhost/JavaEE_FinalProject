package com.github.NynrahGhost.finalProject.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.NynrahGhost.finalProject.validation.PasswordMatches;
import com.github.NynrahGhost.finalProject.validation.ValidEmail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class UserDto {

	@NotEmpty
	@NotNull
    private String username;

	@NotNull
    @NotEmpty
    private String password;

	@NotNull
    @NotEmpty
    private String matchingPassword;

	@ValidEmail
	@NotNull
    @NotEmpty
    private String email;

}
