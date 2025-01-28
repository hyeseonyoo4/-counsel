package com.nuriapp.login.service;

import com.nuriapp.login.domain.Member;
import com.nuriapp.login.dto.MemberDTO;
import com.nuriapp.login.dto.ServiceDTO;
import com.nuriapp.login.mapper.MemberMapper;
import com.nuriapp.login.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MemberService {

    private final MemberMapper memberMapper;
    private final ServiceMapper serviceMapper;
    private final ModelMapper modelMapper;

    public MemberDTO login(MemberDTO memberDTO) {
        Member member = memberMapper.findByIdandPassword(memberDTO.getId(), memberDTO.getPassword());

        if (member != null) {
            MemberDTO loginMember = new MemberDTO();
            modelMapper.map(member, loginMember);
            return loginMember;
        }
        return null;
    }

    public MemberDTO searchId(String id) {
        Member member = memberMapper.findById(id);

        if(member != null) {
            MemberDTO searchMember = new MemberDTO();
            modelMapper.map(member, searchMember);
            return searchMember;
        }
        return null;
    }

    public List<MemberDTO> getMembersByNameAndTel(String userName, String tel) {
        log.info(userName + tel);
        List<Member> members = memberMapper.findMembersByNameAndTel(userName, tel);
        List<MemberDTO> memberDTOs = new ArrayList<>();

        log.info("members : " + members);

        if (members != null) {
            for (Member member : members) {
                MemberDTO memberDTO = new MemberDTO();
                modelMapper.map(member, memberDTO);
                memberDTOs.add(memberDTO);
            }
            log.info(memberDTOs);
            return memberDTOs;
        }
        return null;
    }
    public List<MemberDTO> getMembersByIdAndTel(String id, String tel) {
        log.info("Searching for members with id: " + id + ", tel: " + tel);
        List<Member> members = memberMapper.findMembersByIdAndTel(id, tel);

        List<MemberDTO> memberDTOs = new ArrayList<>();
        if (members != null) {
            for (Member member : members) {
                MemberDTO memberDTO = new MemberDTO();
                modelMapper.map(member, memberDTO);
                memberDTOs.add(memberDTO);
            }
            return memberDTOs;
        }
        return null;
    }

    public List<ServiceDTO> getServiceList(String id, Date startDate, Date endDate) {
        List<com.nuriapp.login.domain.Service> services = serviceMapper.getService(id, startDate, endDate);

        List<ServiceDTO> ServiceHistoryList = new ArrayList<>();

        for (com.nuriapp.login.domain.Service service : services) {
            ServiceDTO serviceDTO = new ServiceDTO();
            modelMapper.map(service, serviceDTO);
            ServiceHistoryList.add(serviceDTO);
        }

        return ServiceHistoryList; // Return the populated list.
    }

    public boolean changePassword(String password, String id){
        int changeRow = memberMapper.changePassword(password, id);
        return changeRow > 0;
    }
}
