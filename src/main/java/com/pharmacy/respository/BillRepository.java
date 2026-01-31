package com.pharmacy.respository;
import com.pharmacy.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

	import java.time.LocalDate;
	import java.util.List;

	public interface BillRepository extends JpaRepository<Bill, Long> {
	    List<Bill> findByDate(LocalDate date);
	    List<Bill> findByDateBetween(LocalDate start, LocalDate end);
	}


