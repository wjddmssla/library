package com.korit.library.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    @ApiModelProperty(hidden = true)
    private int userId;

    @NotBlank
    @ApiModelProperty(name = "username", value="사용자 이름", example="abc", required = true)
    private String username;
    @NotBlank
    @ApiModelProperty(name = "password", value="비밀번호", example="abc", required = true)
    private String password;
    @ApiModelProperty(name = "repassword", value="비밀번호 확인", example="abc", required = true)
    @NotBlank
    private String repassword;
    @NotBlank
    @ApiModelProperty(name = "name", value="이름", example="abc", required = true)
    private String name;
    @NotBlank
    @Email
    @ApiModelProperty(name = "eamil", value="이메일", example="abc", required = true)
    private String email;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<RoleDtlDto> roleDtlDto;
}
