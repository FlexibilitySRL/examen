package ar.com.plug.examen.domain.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.TransactionDetailDTO;
import ar.com.plug.examen.entities.TransactionDetail;

@Component
public class TransactionDetailMapper {

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private ProductMapper productMapper;

	public TransactionDetailDTO transactionDetailToTransactionDetailDTO(TransactionDetail transactionDetail) {
		TransactionDetailDTO transactionItemsApi = new TransactionDetailDTO();
		transactionItemsApi.setProduct(productMapper.productToProductDTO(transactionDetail.getProduct()));
		transactionItemsApi.setQuantity(transactionDetail.getQuantity());
		transactionItemsApi.setTransaction(transactionDetail.getTransaction());
		return transactionItemsApi;
	}

	public TransactionDetail transactionDetailDTOToTransactionDetail(TransactionDetailDTO transactionDetailDTO) {
		TransactionDetail transactionDetail  = new TransactionDetail();
		transactionDetail.setProduct(productMapper.productDTOtoProduct(transactionDetailDTO.getProduct()));
		transactionDetail.setQuantity(transactionDetailDTO.getQuantity());
		transactionDetail.setTransaction(transactionDetailDTO.getTransaction());
		return transactionDetail;
	}

	public List<TransactionDetailDTO> transactionDetailToListTransactionDetailsDTO(List<TransactionDetail> transactionDetail) {
		return transactionDetail.stream().map(this::transactionDetailToTransactionDetailDTO).collect(Collectors.toList());
	}

	public List<TransactionDetail> transactionDetailsDTOToListTransactionDetails(List<TransactionDetailDTO> transactionDetailDTO) {
		return transactionDetailDTO.stream().map(this::transactionDetailDTOToTransactionDetail).collect(Collectors.toList());
	}

}
