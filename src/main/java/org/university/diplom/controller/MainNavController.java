package org.university.diplom.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.university.diplom.dto.CommonWaveDto;

@Controller
public class MainNavController {

    @GetMapping("main")
    public String openPage() {
        return "main";
    }

    @GetMapping("/mechanical")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String displayMechanicalPage(Model model) {
        CommonWaveDto commonWaveDto = new CommonWaveDto();
        model.addAttribute("commonWaveDto", commonWaveDto);
        return "mechanical";
    }

    @GetMapping("/waves")
    public String displayWavesPage(Model model) {
        CommonWaveDto commonWaveDto = new CommonWaveDto();
        model.addAttribute("commonWaveDto", commonWaveDto);
        return "waves";
    }

    @GetMapping("/addition")
    public String displayAdditionPage(Model model) {
        CommonWaveDto commonWaveDto = new CommonWaveDto();
        model.addAttribute("commonWaveDto", commonWaveDto);
        return "addition";
    }

    @GetMapping("/beating")
    public String displayBeatingPage(Model model) {
        CommonWaveDto commonWaveDto = new CommonWaveDto();
        model.addAttribute("commonWaveDto", commonWaveDto);
        return "beating";
    }

    @GetMapping("/interference")
    public String displayInterferencePage(Model model) {
        CommonWaveDto commonWaveDto = new CommonWaveDto();
        model.addAttribute("commonWaveDto", commonWaveDto);
        return "interference";
    }
}
