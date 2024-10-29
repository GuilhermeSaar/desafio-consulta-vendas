package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.projection.ReportMinProjection;
import com.devsuperior.dsmeta.projection.SummaryMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {


	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}



	// busca paginada/customizada dos relatorios

	public Page<ReportMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate endDate;
		LocalDate startDate;

		if ( maxDate == null || maxDate.isEmpty()) {
			 endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			endDate = LocalDate.parse(maxDate);

		}
		if (minDate == null || minDate.isEmpty()) {
			startDate = endDate.minusYears(1);
		}
		else {
			startDate = LocalDate.parse(minDate);
		}
		//log
		//System.out.println("Start Date: " + startDate + ", End Date: " + endDate + ", Name: " + name);

		Page<ReportMinProjection> projection = repository.reportSearch(startDate, endDate, name, pageable);

		return projection.map(ReportMinDTO::new);
	}


	// sumario de vendas por vendedor
	public List<SummaryMinDTO> getSummary(String minDate, String maxDate) {

		LocalDate startDate;
		LocalDate endDate;

		if (maxDate == null || maxDate.isEmpty()) {
			endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {endDate = LocalDate.parse(maxDate);}

		if (minDate == null || minDate.isEmpty()) {
			startDate = endDate.minusYears(1);
		}
		else {startDate = LocalDate.parse(minDate);}

		//log
		//System.out.println("Start Date: " + startDate + ", End Date: " + endDate);

		List<SummaryMinProjection> projections = repository.summarySearch(startDate, endDate);

		return projections.stream().map(x -> new SummaryMinDTO(x)).collect(Collectors.toList());

	}










}
