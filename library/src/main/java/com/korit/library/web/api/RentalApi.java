package com.korit.library.web.api;

import com.korit.library.aop.annotation.ParamsAspect;
import com.korit.library.security.PrincipalDetails;
import com.korit.library.service.RentalService;
import com.korit.library.web.dto.CMRespDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"도서 대여 API"})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalApi {
    /*
        /rental/{bookId}
        대여 요청 -> 대여 요청 날린 사용자의 대여가능 여부확인
        -> 가능함(대여 가능 횟수 3권 미만일 때) -> 대여 정보 추가 rental_mst(대여코드), rental_dtl
        -> 불가능함(대여 가능 횟수 0이면) -> 예외처리
     */

    @PostMapping("/rental/{bookId}")
    public ResponseEntity<CMRespDto<?>> rental(@PathVariable int bookId,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        RentalService.rentalOnePrincipalDetails.getUser().getUserId().bookId();
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", null));
    }
}
