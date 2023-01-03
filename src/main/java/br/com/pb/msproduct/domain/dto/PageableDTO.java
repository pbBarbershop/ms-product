package br.com.pb.msproduct.domain.dto;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageableDTO {

    private Integer numberOfElements;
    private Long totalElements;
    private Integer totalPages;
    private List<ProductDTO> paymentsList;
}
