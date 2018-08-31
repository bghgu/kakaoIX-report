package com.kakaoix.report.api;

import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.service.ProductService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ds on 2018-08-31.
 */

@RestController
@RequestMapping("products")
public class ProductController {

    private static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<DefaultRes<Iterable<Product>>> getAllProducts(
            @RequestParam(value = "page_no", defaultValue = "1", required = false) final int page_no,
            @RequestParam(value = "page_size", defaultValue = "10", required = false) final int page_size
    ) {
        try {
            return new ResponseEntity<DefaultRes<Iterable<Product>>>(productService.findAll(page_no, page_size), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Iterable<Product>>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{product_idx}")
    public ResponseEntity<DefaultRes<Product>> getUsers(@PathVariable final int product_id) {
        try {
            return new ResponseEntity<DefaultRes<Product>>(productService.findOne(product_id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Product>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
