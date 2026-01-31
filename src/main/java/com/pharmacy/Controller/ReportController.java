package com.pharmacy.Controller;

import com.pharmacy.respository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired private BillRepository billRepo;

    @GetMapping("/daily")
    public String dailyReport(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("bills", billRepo.findByDate(today));
        return "daily-report";
    }

    @GetMapping("/monthly")
    public String monthlyReport(Model model) {
        YearMonth ym = YearMonth.now();
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();
        model.addAttribute("bills", billRepo.findByDateBetween(start, end));
        return "monthly-report";
    }
}
