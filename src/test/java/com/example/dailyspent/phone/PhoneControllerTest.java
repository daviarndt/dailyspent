package com.example.dailyspent.phone;

import com.example.dailyspent.phone.dto.DescribePhoneDTO;
import com.example.dailyspent.phone.dto.SavePhoneDTO;
import com.example.dailyspent.utils.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<SavePhoneDTO> savePhoneDTOJacksonTester;

    @Autowired
    private JacksonTester<ApiResponse<DescribePhoneDTO>> apiResponseJacksonTester;

    @MockBean
    private PhoneService phoneService;

    @Test
    @DisplayName("Should return Http Status Code 400 when request body is wrong")
    @WithMockUser
    void savePhone400() throws Exception {
        var response = mockMvc.perform(
                post("/phone")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return Http Status Code 200 when request body is correct")
    @WithMockUser
    void savePhone200() throws Exception {
        var describePhoneDTO = new DescribePhoneDTO(null, "55", "911111111", null);

        when(phoneService.savePhone(any(), any())).thenReturn(describePhoneDTO);

        var response = mockMvc.perform(
                post("/phone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(savePhoneDTOJacksonTester.write(
                                new SavePhoneDTO("55", "911111111")
                        ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = apiResponseJacksonTester.write(
                new ApiResponse<>(true, "Operation carried out successfully", describePhoneDTO)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}