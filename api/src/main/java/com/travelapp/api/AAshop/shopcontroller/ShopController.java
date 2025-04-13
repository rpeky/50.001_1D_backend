package com.travelapp.api.AAshop.shopcontroller;

import com.travelapp.api.AAshop.DTOs.cart.CartReadDTO;
import com.travelapp.api.AAshop.DTOs.cartitems.CartItemsCreateDTO;
import com.travelapp.api.AAshop.DTOs.cartitems.CartItemsReadDTO;
import com.travelapp.api.AAshop.DTOs.cartitems.CartItemsUpdateDTO;
import com.travelapp.api.AAshop.DTOs.productreviews.ProductReviewsCreateDTO;
import com.travelapp.api.AAshop.DTOs.productreviews.ProductReviewsUpdateDTO;
import com.travelapp.api.AAshop.DTOs.productreviews.ProductReviewsReadDTO;
import com.travelapp.api.AAshop.DTOs.products.ProductsCreateDTO;
import com.travelapp.api.AAshop.DTOs.products.ProductsReadDTO;
import com.travelapp.api.AAshop.DTOs.products.ProductsUpdateDTO;
import com.travelapp.api.AAshop.DTOs.purchasehistory.PurchaseHistoryReadListDTO;
import com.travelapp.api.AAshop.DTOs.purchasehistory.ReceiptDTO;
import com.travelapp.api.AAshop.shopservice.ShopService;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiRequest;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // Product endpoints
    @PostMapping("/products")
    public ResponseEntity<ApiResponse<ProductsReadDTO>> createProduct(@RequestBody ApiRequest<ProductsCreateDTO> request) {
        ProductsReadDTO created = shopService.createProduct(request.getData());
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<ApiResponse<ProductsReadDTO>> updateProduct(@RequestBody ApiRequest<ProductsUpdateDTO> request) {
        ProductsReadDTO updated = shopService.updateProduct(request.getData());
        return new ResponseEntity<>(new ApiResponse<>(updated), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ApiResponse<ProductsReadDTO>> getProduct(@PathVariable Long productId) {
        ProductsReadDTO product = shopService.getProduct(productId);
        return new ResponseEntity<>(new ApiResponse<>(product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long productId) {
        shopService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Product Review endpoints
    @PostMapping("/reviews")
    public ResponseEntity<ApiResponse<ProductReviewsReadDTO>> createReview(@RequestBody ApiRequest<ProductReviewsCreateDTO> request) {
        ProductReviewsReadDTO created = shopService.createReview(request.getData());
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    @PutMapping("/reviews")
    public ResponseEntity<ApiResponse<ProductReviewsReadDTO>> updateReview(@RequestBody ApiRequest<ProductReviewsUpdateDTO> request) {
        ProductReviewsReadDTO updated = shopService.updateReview(request.getData());
        return new ResponseEntity<>(new ApiResponse<>(updated), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<ProductReviewsReadDTO>> getReview(@PathVariable Long reviewId) {
        ProductReviewsReadDTO review = shopService.getReview(reviewId);
        return new ResponseEntity<>(new ApiResponse<>(review), HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long reviewId) {
        shopService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //GetCart
    @GetMapping("/cart/{userUid}")
    public ResponseEntity<ApiResponse<CartReadDTO>> getCart(@PathVariable String userUid) {
        CartReadDTO cart = shopService.getCart(userUid);
        return new ResponseEntity<>(new ApiResponse<>(cart), HttpStatus.OK);
    }

    // CartItem endpoints
    @PostMapping("/cartitems")
    public ResponseEntity<ApiResponse<CartItemsReadDTO>> createCartItem(@RequestBody ApiRequest<CartItemsCreateDTO> request) {
        CartItemsReadDTO created = shopService.createCartItem(request.getData());
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    @PutMapping("/cartitems")
    public ResponseEntity<ApiResponse<CartItemsReadDTO>> updateCartItem(@RequestBody ApiRequest<CartItemsUpdateDTO> request) {
        CartItemsReadDTO updated = shopService.updateCartItem(request.getData());
        return new ResponseEntity<>(new ApiResponse<>(updated), HttpStatus.OK);
    }

    @DeleteMapping("/cartitems/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteCartItem(@PathVariable Long cartItemId) {
        shopService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Purchase processing
    @PostMapping("/purchase/{cartId}")
    public ResponseEntity<ApiResponse<ReceiptDTO>> processPurchase(@PathVariable Long cartId) {
        ReceiptDTO receipt = shopService.processPurchase(cartId);
        return new ResponseEntity<>(new ApiResponse<>(receipt), HttpStatus.CREATED);
    }

    @GetMapping("/purchase/history/{userUid}")
    public ResponseEntity<ApiResponse<List<PurchaseHistoryReadListDTO>>> getPurchaseHistory(@PathVariable String userUid) {
        List<PurchaseHistoryReadListDTO> history = shopService.getUserPurchaseHistory(userUid);
        return new ResponseEntity<>(new ApiResponse<>(history), HttpStatus.OK);
    }
}

