package com.pharmacy.respository;


	import com.pharmacy.model.Medicine;
	import org.springframework.data.jpa.repository.JpaRepository;

	public interface MedicineRepository extends JpaRepository<Medicine, Long> {}


