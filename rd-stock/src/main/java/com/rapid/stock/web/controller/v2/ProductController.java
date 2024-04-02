package com.rapid.stock.web.controller.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.service.v2.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/products")
@AllArgsConstructor
@Profile("rational-db")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody ParentProductSaveRequest ppSaveRequest){
           productService.save(ppSaveRequest);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<ParentProduct>> findPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Page<ParentProduct> parentProductsPage = productService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(parentProductsPage);
    }
}
