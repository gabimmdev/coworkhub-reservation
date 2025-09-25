package com.fiap.workspaces.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fiap.workspaces.model.Workspace;
import com.fiap.workspaces.repository.WorkspaceRepository;

@Controller
@RequestMapping("/workspaces")
public class WorkspaceController {

  private final WorkspaceRepository repo;

  public WorkspaceController(WorkspaceRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("workspaces", repo.findAll());
    model.addAttribute("workspace", new Workspace());
    return "workspaces/list";
  }

  @PostMapping
  public String create(@ModelAttribute("workspace") @Valid Workspace workspace, BindingResult br, Model model) {
    if (br.hasErrors()) {
      model.addAttribute("workspaces", repo.findAll());
      return "workspaces/list";
    }
    repo.save(workspace);
    return "redirect:/workspaces";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id) {
    repo.deleteById(id);
    return "redirect:/workspaces";
  }
}