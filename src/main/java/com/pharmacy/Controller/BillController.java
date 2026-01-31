package com.pharmacy.Controller;

import com.pharmacy.model.Bill;
import com.pharmacy.model.Medicine;
import com.pharmacy.respository.BillRepository;
import com.pharmacy.respository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired private MedicineRepository medicineRepo;
    @Autowired private BillRepository billRepo;

    @GetMapping("/buy")
    public String buyMedicinePage(Model model) {
        model.addAttribute("medicines", medicineRepo.findAll());
        model.addAttribute("bill", new Bill());
        return "buy-medicine";
    }

    @PostMapping("/generate")
    public String generateBill(@ModelAttribute Bill bill) {
        Medicine med = medicineRepo.findById(bill.getMedicineId()).orElseThrow();

        int quantity = bill.getQuantity();
        if (quantity > med.getStock()) {
            return "redirect:/bill/buy?error=stock";
        }

        double total = med.getPrice() * quantity;
        bill.setTotalAmount(total);
        bill.setDate(LocalDate.now());

        med.setStock(med.getStock() - quantity);
        medicineRepo.save(med);

        billRepo.save(bill);
        return "redirect:/bill/view/" + bill.getId();
    }

    @GetMapping("/view/{id}")
    public String viewBill(@PathVariable Long id, Model model) {
        model.addAttribute("bill", billRepo.findById(id).orElseThrow());
        return "view-bill";
    }

    @GetMapping("/print/{id}")
    public String printBill(@PathVariable Long id, Model model) {
        model.addAttribute("bill", billRepo.findById(id).orElseThrow());
        return "print-bill";
    }
}
