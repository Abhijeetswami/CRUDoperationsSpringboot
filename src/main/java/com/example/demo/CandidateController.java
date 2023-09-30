package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/")
    public String listCandidates(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);
        model.addAttribute("candidate", new Candidate());
        return "register";
    }

    @PostMapping("/register")
    public String registerCandidate(@ModelAttribute Candidate candidate) {
        candidateRepository.save(candidate);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        model.addAttribute("candidate", candidate);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        candidateRepository.deleteById(id);
        return "redirect:/";
    }
}

