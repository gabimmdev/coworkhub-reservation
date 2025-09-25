package br.com.fiap.epictaskg.workspace;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workspaces")
public class WorkspaceController {
    private final WorkspaceRepository workspaceRepository;

    public WorkspaceController(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("workspaces", workspaceRepository.findAll());
        model.addAttribute("workspace", new Workspace());  // Adicionar objeto workspace vazio para o formulário
        return "workspaces/list";
    }

    @GetMapping("/new")
    public String formNew(Model model) {
        model.addAttribute("workspace", new Workspace());
        return "workspaces/form";
    }

    @PostMapping
    public String create(@ModelAttribute Workspace workspace) {
        if (workspace.getActive() == null) workspace.setActive(Boolean.TRUE);
        workspaceRepository.save(workspace);
        return "redirect:/workspaces";
    }

    @GetMapping("/{id}/edit")
    public String formEdit(@PathVariable Long id, Model model) {
        model.addAttribute("workspace", workspaceRepository.findById(id).orElseThrow());
        return "workspaces/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Workspace form) {
        Workspace r = workspaceRepository.findById(id).orElseThrow();
        r.setName(form.getName());
        r.setCapacity(form.getCapacity());
        r.setLocation(form.getLocation());
        r.setActive(form.getActive() == null ? Boolean.TRUE : form.getActive());
        workspaceRepository.save(r);
        return "redirect:/workspaces";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        workspaceRepository.deleteById(id);
        return "redirect:/workspaces";
    }
}
