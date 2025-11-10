package org.university.diplom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.controller.BeatingGenerationController;
import org.university.diplom.dto.CommonWaveDto;
import org.university.diplom.dto.ResultDto;
import org.university.diplom.processor.WaveFunctionProcessor;
import org.university.diplom.service.impl.MinioService;

import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(BeatingGenerationController.class)
public class BeatingGenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WaveFunctionProcessor waveFunctionProcessor;

    @Mock
    private MinioService minioService;

    @Test
    public void testProcess() throws Exception {
        CommonWaveDto commonWaveDto = new CommonWaveDto(0.0, 1.0, 0.0, 0.0, 1.2, 1.2, 1.0, FunctionType.BEATING);

        ResultDto resultDto = new ResultDto();

        Mockito.when(waveFunctionProcessor.process(Mockito.any(CommonWaveDto.class))).thenReturn(resultDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/beating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commonWaveDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("beating"))
                .andExpect(MockMvcResultMatchers.model().attribute("resultDto", resultDto));
    }

    @Test
    public void testDownloadImage() throws Exception {

        UUID imageName = UUID.randomUUID();
        byte[] imageBytes = new byte[]{};

        Mockito.when(minioService.find(Mockito.anyString(), Mockito.anyString())).thenReturn(imageBytes);

        mockMvc.perform(MockMvcRequestBuilders.get("/beating/image/{imageName}", imageName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes(imageBytes));
    }


    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}