package com.kakaoix.report.api;

import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Pagination;
import com.kakaoix.report.service.ProductService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ds on 2018-08-31.
 */

@RestController
@RequestMapping("products")
public class ProductController {

    /**
     * 실패 시 기본 반환 Response
     */
    private static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final ProductService productService;

    /**
     * 서비스 의존성 주입
     */
    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 목록 조회
     * @param offset 목록 시작, 기본값 0
     * @param limit 조회할 목록 갯수, 기본값 10
     * @param sort 정렬 기준, 기본값 상품 고유 IDX
     * @param order 정렬 방법, 기본값 내림차순
     * @return 상품 목록
     */
    @GetMapping("")
    public ResponseEntity<DefaultRes<Iterable<Product>>> getAllProducts(
            @RequestParam(value = "offset", defaultValue = "0", required = false) final int offset,
            @RequestParam(value = "limit", defaultValue = "10", required = false) final int limit,
            @RequestParam(value = "sort", defaultValue = "productIdx", required = false) final String sort,
            @RequestParam(value = "order", defaultValue = "desc", required = false) final String order
    ) {
        try {
            Pagination pagination;
            if(order.equals("asc")) {
                pagination = Pagination.builder()
                        .offset(offset)
                        .limit(limit)
                        .sort(new Sort(Sort.Direction.ASC, sort))
                        .build();
            }else {
                pagination = Pagination.builder()
                        .offset(offset)
                        .limit(limit)
                        .sort(new Sort(Sort.Direction.DESC, sort))
                        .build();
            }
            return new ResponseEntity<>(productService.findAll(pagination), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Iterable<Product>>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 상품 정보 조회
     * @param product_idx 상품 고유 IDX
     * @return 상품 정보
     */
    @GetMapping("/{product_idx}")
    public ResponseEntity<DefaultRes<Product>> getProduct(@PathVariable final int product_idx) {
        try {
            return new ResponseEntity<>(productService.findOne(product_idx), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Product>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
