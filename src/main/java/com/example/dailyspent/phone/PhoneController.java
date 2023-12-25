package com.example.dailyspent.phone;

import com.example.dailyspent.phone.dto.DescribePhoneDTO;
import com.example.dailyspent.phone.dto.SavePhoneDTO;
import com.example.dailyspent.user.dto.DescribeUserDTO;
import com.example.dailyspent.utils.ApiResponse;
import com.example.dailyspent.utils.exceptions.IdIsIllegalException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("phone")
@SecurityRequirement(name = "bearer-key")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Transactional
    @PostMapping()
    public ResponseEntity<ApiResponse<DescribePhoneDTO>> savePhone(@Valid @RequestBody SavePhoneDTO savePhoneDTO, Principal principal) {
        DescribePhoneDTO describePhoneDTO = phoneService.savePhone(savePhoneDTO, principal.getName());
        return new ResponseEntity<>(ApiResponse.success(describePhoneDTO), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{phoneId}")
    public ResponseEntity<ApiResponse<DescribeUserDTO>> deletePhoneById(@PathVariable Long phoneId, Principal principal) {
        if (phoneId.toString().isBlank() || phoneId <= 0) {
            throw new IdIsIllegalException();
        }
        DescribeUserDTO describeUserDTO = phoneService.deletePhoneById(phoneId, principal.getName());
        return new ResponseEntity<>(ApiResponse.success(describeUserDTO), HttpStatus.OK);
    }
}
