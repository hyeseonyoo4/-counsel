package com.nuriapp.login.controller;

import com.nuriapp.login.dto.MemberDTO;
import com.nuriapp.login.dto.ResponseDTO;
import com.nuriapp.login.dto.ServiceDTO;
import com.nuriapp.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RequiredArgsConstructor
@RestController
@Log4j2
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        MemberDTO loginMember = new MemberDTO();
        loginMember = memberService.login(memberDTO);
        if (loginMember != null) {
            responseDTO.setValue(loginMember);
            responseDTO.setSuccess("로그인에 성공하였습니다.");
            return ResponseEntity.ok(responseDTO);
        }

        responseDTO.setError("아이디나 비밀번호가 올바르지 않습니다.");
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @PostMapping("/searchMemberId")
    public ResponseEntity<?> searchMemberId(@RequestBody MemberDTO memberDTO) {
        ResponseDTO<List<MemberDTO>> responseDTO = new ResponseDTO<>();
        List<MemberDTO> searchMembers = memberService.getMembersByNameAndTel(memberDTO.getUserName(), memberDTO.getTel());

        log.info("test : " + memberDTO);

        if (!searchMembers.isEmpty()) {
            responseDTO.setValue(searchMembers);
            return ResponseEntity.ok(responseDTO);
        }

        responseDTO.setError("찾은 멤버가 없습니다.");
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @PostMapping("/searchMemberPw")
    public ResponseEntity<?> searchMemberPw(@RequestBody MemberDTO memberDTO) {
        ResponseDTO<List<MemberDTO>> responseDTO = new ResponseDTO<>();
        List<MemberDTO> searchMembers = memberService.getMembersByIdAndTel(memberDTO.getId(), memberDTO.getTel());

        if (!searchMembers.isEmpty()) {
            responseDTO.setValue(searchMembers);
            return ResponseEntity.ok(responseDTO);
        } else {
            // 검색 결과가 없을 때 처리
            responseDTO.setError("검색된 회원이 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
    }

    @GetMapping("/serviceHistoryList")
    public ResponseEntity<?> getServiceHistoryList( @RequestParam("id") String id,
                                                    @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                    @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        log.info("controller : " + id);
        ResponseDTO<List<ServiceDTO>> responseDTO = new ResponseDTO<>();
        List<ServiceDTO> serviceList = memberService.getServiceList(id, startDate, endDate);

        if (!serviceList.isEmpty()) {
            responseDTO.setValue(serviceList);
            return ResponseEntity.ok(responseDTO);
        } else {
            // 검색 결과가 없을 때 처리
            responseDTO.setError("검색된 상담 내역이 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody MemberDTO memberDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        log.info("id : " + memberDTO.getId()); log.info("password : " + memberDTO.getPassword());
        if(memberService.changePassword(memberDTO.getPassword(), memberDTO.getId())){
            responseDTO.setSuccess("비밀번호 변경 성공");
          return ResponseEntity.ok(responseDTO);
        }
        responseDTO.setError("비밀번호 변경 실패");
        return ResponseEntity.badRequest().body(responseDTO);
    }

}
