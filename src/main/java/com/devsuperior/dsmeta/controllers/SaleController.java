package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.projection.SummaryMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}


	// lista dos relatorios paginados
	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportMinDTO>> getReport(
			@RequestParam(name = "minDate", required = false) String minDate,
			@RequestParam(name = "maxDate", required = false) String maxDate,
			@RequestParam(defaultValue = "") String name,
			Pageable pageable) {


		Page<ReportMinDTO> dto = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	// lista dos sumario de vendas por vendedor
	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryMinDTO>> getSummary(
			@RequestParam(name = "minDate", required = false) String startDate,
			@RequestParam(name = "maxDate", required = false) String endDate) {

		List<SummaryMinDTO> dto = service.getSummary(startDate, endDate);
		return ResponseEntity.ok(dto);
	}


	}



