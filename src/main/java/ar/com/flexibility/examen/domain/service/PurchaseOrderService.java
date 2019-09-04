package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.dto.PurchaseOrderDTO;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;

public interface PurchaseOrderService {

	public List<PurchaseOrderDTO> findAll();

	public PurchaseOrderDTO findById(Long id);

	public PurchaseOrderDTO save(PurchaseOrderDTO dto);

	public PurchaseOrderDTO entityToDto(PurchaseOrder entity);

	public PurchaseOrder dtoToEntity(PurchaseOrderDTO dto);

}
