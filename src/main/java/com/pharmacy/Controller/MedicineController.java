package com.pharmacy.Controller;

import com.pharmacy.model.Medicine;
import com.pharmacy.respository.MedicineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicines")
public class MedicineController {
    @Autowired private MedicineRepository medicineRepo;

    @GetMapping
    public String listMedicines(Model model) {
        model.addAttribute("medicines", medicineRepo.findAll());
        return "medicine-list";
    }

    @GetMapping("/add")
    public String addMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "medicine-form";
    }

    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute Medicine medicine) {
        medicineRepo.save(medicine);
        return "redirect:/medicines";
    }

    @GetMapping("/edit/{id}")
    public String editMedicine(@PathVariable Long id, Model model) {
        model.addAttribute("medicine", medicineRepo.findById(id).orElseThrow());
        return "medicine-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        medicineRepo.deleteById(id);
        return "redirect:/medicines";
    }
}
