package gift.controller;

import gift.ProductService;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 등록 API
     * @param requestDto 이름과 가격 이미지 URL
     */
    @PostMapping("/add")
    public void save(@RequestBody ProductRequestDto requestDto){
        productService.addProduct(requestDto);
    }

    @GetMapping("/all")
    public List<ProductResponseDto> getAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable("id") Long id){
        return productService.findProduct(id);
    }

    @PutMapping("/edit/{id}")
    public ProductResponseDto editProduct(
            @PathVariable("id") Long id,
            @RequestBody ProductRequestDto request){
        return productService.editProduct(id,request);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteProduct(@RequestParam("id") Long id){
        productService.deleteProduct(id);
        return HttpStatus.NO_CONTENT;
    }
}
