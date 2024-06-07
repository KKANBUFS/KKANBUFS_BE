package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.User;
import com.betrue.kkanbufs_be.exception.Unauthorized;
import com.betrue.kkanbufs_be.exception.UserNotFound;
import com.betrue.kkanbufs_be.repository.PartnerShipRepository;
import com.betrue.kkanbufs_be.repository.user.CollegeRepository;
import com.betrue.kkanbufs_be.request.CollegeSignup;
import com.betrue.kkanbufs_be.request.PartnerShipCreate;
import com.betrue.kkanbufs_be.request.Search;
import com.betrue.kkanbufs_be.response.PartnerShipResponse;
import com.betrue.kkanbufs_be.response.user.CollegeResponse;
import com.betrue.kkanbufs_be.response.user.PartnerResponse;
import com.betrue.kkanbufs_be.service.AuthService;
import com.betrue.kkanbufs_be.service.PartnerShipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/college")
public class CollegeController {

    private final AuthService authService;

    private final CollegeRepository collegeRepository;

    private final PartnerShipService partnerShipService;

    private final PartnerShipRepository partnerShipRepository;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid CollegeSignup signup){
        authService.signup(signup);
    }

    @GetMapping("/{partnerName}_detail")
    public CollegeResponse getCollege(@PathVariable String partnerName){
        College college = collegeRepository.findByName(partnerName).orElseThrow(UserNotFound::new);
        List<PartnerShip> listByCollege = partnerShipRepository.getListByCollege(college);

        return CollegeResponse.builder()
                .name(college.getName())
                .loginId(college.getLoginId())
                .instagram(college.getInstagram())
                .partnerShips(listByCollege)
                .build();
    }


    @PostMapping("/partnership")//college 체킹
    public void connection(@RequestBody PartnerShipCreate partnerShipCreate, NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null || session.equals("")) {
            throw new Unauthorized();
        }

        partnerShipService.connection(partnerShipCreate,session);
    }

    @GetMapping("/partnerships")
    public List<PartnerShipResponse> getAll(@ModelAttribute Search search){
         return partnerShipService.getList(search);
    }

    //조회
    @GetMapping("/my")
    public CollegeResponse get(NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null || session.equals("")) {
            throw new Unauthorized();
        }

        User user = session.getUser();

        College college = collegeRepository.findByLoginId(user.getLoginId()).orElseThrow(UserNotFound::new);

        return CollegeResponse.builder()
                .name(college.getName())
                .loginId(college.getLoginId())
                .instagram(college.getInstagram())
                .build();
    }

    //todo 수정
    //todo 삭제
}
