package com.travelapp.api.shop.service;

import com.travelapp.api.shop.DTOs.cart.CartOtherCreateDTO;
import com.travelapp.api.shop.DTOs.cart.CartOtherReadDTO;
import com.travelapp.api.shop.DTOs.cart.CartReadDTO;
import com.travelapp.api.shop.DTOs.cartitems.CartItemsCreateDTO;
import com.travelapp.api.shop.DTOs.cartitems.CartItemsReadDTO;
import com.travelapp.api.shop.DTOs.cartitems.CartItemsUpdateDTO;
import com.travelapp.api.shop.DTOs.productreviews.ProductReviewsCreateDTO;
import com.travelapp.api.shop.DTOs.productreviews.ProductReviewsUpdateDTO;
import com.travelapp.api.shop.DTOs.productreviews.ProductReviewsReadDTO;
import com.travelapp.api.shop.DTOs.productreviews.other.ProductReviewsOtherReadDTO;
import com.travelapp.api.shop.DTOs.products.ProductsCreateDTO;
import com.travelapp.api.shop.DTOs.products.ProductsReadDTO;
import com.travelapp.api.shop.DTOs.products.ProductsUpdateDTO;
import com.travelapp.api.shop.DTOs.products.other.ProductsOtherCreateDTO;
import com.travelapp.api.shop.DTOs.products.other.ProductsOtherReadDTO;
import com.travelapp.api.shop.DTOs.products.other.ProductsOtherUpdateDTO;
import com.travelapp.api.shop.DTOs.purchasehistory.PurchaseHistoryReadListDTO;
import com.travelapp.api.shop.DTOs.purchasehistory.ReceiptDTO;
import com.travelapp.api.shop.entities.Cart;
import com.travelapp.api.shop.entities.CartItems;
import com.travelapp.api.shop.entities.ProductReviews;
import com.travelapp.api.shop.entities.Products;
import com.travelapp.api.shop.entities.PurchaseHistory;
import com.travelapp.api.shop.repositories.CartItemsRepository;
import com.travelapp.api.shop.repositories.CartRepository;
import com.travelapp.api.shop.repositories.ProductReviewsRepository;
import com.travelapp.api.shop.repositories.ProductsRepository;
import com.travelapp.api.shop.repositories.PurchaseHistoryRepository;
import com.travelapp.api.ratings.RatingsCalculator.RatingsCalculator;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherUpdateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShopService {

    @Autowired
    @Qualifier("defaultModelMapper")
    ModelMapper defaultMapper;

    @Autowired
    @Qualifier("strictModelMapper")
    ModelMapper strictMapper;

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ProductReviewsRepository reviewsRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;

    public ProductsReadDTO createProduct(ProductsCreateDTO createDTO) {

        if (createDTO.getName() == null) {
            throw new IllegalArgumentException("Name information is required for product creation.");
        }
        if (createDTO.getType() == null) {
            throw new IllegalArgumentException("Type information is required for product creation");
        }

        Products productToCreate = defaultMapper.map(createDTO, Products.class);
        Products productCreated = productsRepository.save(productToCreate);
        return strictMapper.map(productCreated, ProductsReadDTO.class);
    }

    public ProductsReadDTO updateProduct(ProductsUpdateDTO updateDTO) {
        if (updateDTO.getProductId().isPresent()) {
            Long productId = updateDTO.getProductId().get();
            Optional<Products> optionalProduct = productsRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Products productToUpdate = optionalProduct.get();
                if (updateDTO.getName().isPresent()) {
                    productToUpdate.setName(updateDTO.getName().get());
                }
                if (updateDTO.getType().isPresent()) {
                    productToUpdate.setType(updateDTO.getType().get());
                }
                if (updateDTO.getPrice().isPresent()) {
                    productToUpdate.setPrice(updateDTO.getPrice().get());
                }
                if (updateDTO.getImage().isPresent()){
                    productToUpdate.setImage(updateDTO.getImage().get());
                }
                if (updateDTO.getStock().isPresent()) {
                    productToUpdate.setStock(updateDTO.getStock().get());
                }
                Products productUpdated = productsRepository.save(productToUpdate);
                return strictMapper.map(productUpdated, ProductsReadDTO.class);
            } else {
                throw new EntityNotFoundException("Product with productID " + productId + " not found.");
            }
        } else {
            throw new EntityNotFoundException("ProductID information is required for product update.");
        }
    }

    public ProductsReadDTO getProduct(Long productId) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));

        // manual mapping
        ProductsReadDTO dto = new ProductsReadDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setType(product.getType());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());
        dto.setStock(product.getStock());
        dto.setPurchaseCount(product.getPurchaseCount());

        // compute average rating
        List<ProductReviews> productReviewsList = product.getReviews();
        List<ProductReviews> allProductReviewsList = reviewsRepository.findAll();
        dto.setRating(RatingsCalculator.computeBayesianAverageProducts(productReviewsList, allProductReviewsList, 10));

        // map productReviewsList
        if (productReviewsList != null && !productReviewsList.isEmpty()) {
            List<ProductReviewsOtherReadDTO> reviewDTOs = productReviewsList.stream().map(review -> {
                ProductReviewsOtherReadDTO reviewDTO = new ProductReviewsOtherReadDTO();
                reviewDTO.setReviewId(review.getReviewId());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setReview(review.getReview());
                reviewDTO.setVerifiedPurchase(Boolean.TRUE.equals(review.getVerifiedPurchase()));

                // nested user
                UsersOtherReadDTO userDTO = new UsersOtherReadDTO();
                userDTO.setUserUid(review.getCreatedBy().getUserUid());
                userDTO.setUserName(review.getCreatedBy().getUserName());
                reviewDTO.setCreatedBy(userDTO);

                return reviewDTO;
            }).toList();

            dto.setReviews(reviewDTOs);
        }

        return dto;
    }



    public void deleteProduct(Long productId) {
        if (!productsRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product with ID " + productId + " not found.");
        }
        productsRepository.deleteById(productId);
    }

    public ProductReviewsReadDTO createReview(ProductReviewsCreateDTO createDTO) {
        if (createDTO.getProduct() == null || createDTO.getProduct().getProductId() == null) {
            throw new IllegalArgumentException("Product information with ID is required.");
        }

        Long productId = createDTO.getProduct().getProductId();
        Optional<Products> optionalProduct = productsRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new EntityNotFoundException("Product with ID " + productId + " not found.");
        }

        if (createDTO.getCreatedBy() == null || createDTO.getCreatedBy().getUserUid() == null) {
            throw new IllegalArgumentException("User information with UID is required.");
        }

        String userUid = createDTO.getCreatedBy().getUserUid();
        Optional<Users> optionalUser = usersRepository.findByUserUid(userUid);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found.");
        }

        if (createDTO.getRating() == null || createDTO.getReview() == null) {
            throw new IllegalArgumentException("Both rating and review text are required.");
        }

        Products product = optionalProduct.get();
        Users user = optionalUser.get();

        List<PurchaseHistory> purchaseHistory = purchaseHistoryRepository.findByUser_UserUid(user.getUserUid());
        Boolean hasPurchased = false;
        for (PurchaseHistory purchase : purchaseHistory) {
            if (purchase.getProduct().getProductId().equals(product.getProductId())) {
                hasPurchased = true;
                break;
            }
        }
        if (!hasPurchased) {
            throw new IllegalArgumentException("User must have purchased this product to leave a review.");
        }

        ProductReviews reviewToCreate = new ProductReviews();
        reviewToCreate.setProduct(product);
        reviewToCreate.setCreatedBy(user);
        reviewToCreate.setRating(createDTO.getRating());
        reviewToCreate.setReview(createDTO.getReview());

        ProductReviews reviewCreated = reviewsRepository.save(reviewToCreate);
        return defaultMapper.map(reviewCreated, ProductReviewsReadDTO.class);
    }

    public ProductReviewsReadDTO getReview(Long reviewId) {
        Optional<ProductReviews> optionalProductReviews = reviewsRepository.findById(reviewId);
        if (optionalProductReviews.isPresent()) {
            ProductReviews reviewToShow = optionalProductReviews.get();
            return strictMapper.map(reviewToShow, ProductReviewsReadDTO.class);
        } else {
            throw new EntityNotFoundException("Review with review ID " + reviewId + " not found.");
        }
    }

    public ProductReviewsReadDTO updateReview(ProductReviewsUpdateDTO updateDTO)
            throws EntityNotFoundException, IllegalArgumentException {

        if (updateDTO.getReviewId() == null) {
            throw new IllegalArgumentException("Review ID is required for update.");
        }

        ProductReviews existingReview = reviewsRepository.findById(updateDTO.getReviewId())
                .orElseThrow(() -> new EntityNotFoundException("Review with ID " + updateDTO.getReviewId() + " not found."));

        if (updateDTO.getCreatedBy().isPresent()) {
            UsersOtherUpdateDTO userDTO = updateDTO.getCreatedBy().get();
            if (userDTO.getUserUid().isPresent()) {
                String userUid = userDTO.getUserUid().get();
                if (!usersRepository.existsByUserUid(userUid)) {
                    throw new IllegalArgumentException("Valid user information is required for review update.");
                }
                if (!existingReview.getCreatedBy().getUserUid().equals(userUid)) {
                    throw new IllegalArgumentException("This review was not created by the provided user.");
                }
            } else {
                throw new IllegalArgumentException("User UID is required in createdBy.");
            }
        }

        if (updateDTO.getProduct().isPresent()) {
            ProductsOtherUpdateDTO productDTO = updateDTO.getProduct().get();
            if (productDTO.getProductId().isPresent()) {
                Long productId = productDTO.getProductId().get();
                if (!productsRepository.existsById(productId)) {
                    throw new IllegalArgumentException("Valid product information is required for review update.");
                }
                if (!existingReview.getProduct().getProductId().equals(productId)) {
                    throw new IllegalArgumentException("This review does not belong to the provided product.");
                }
            } else {
                throw new IllegalArgumentException("Product ID is required in product.");
            }
        }

        if (updateDTO.getRating().isPresent()) {
            existingReview.setRating(updateDTO.getRating().get());
        }

        if (updateDTO.getReview().isPresent()) {
            existingReview.setReview(updateDTO.getReview().get());
        }

        if (updateDTO.getVerifiedPurchase().isPresent()) {
            existingReview.setVerifiedPurchase(updateDTO.getVerifiedPurchase().get());
        }

        ProductReviews updated = reviewsRepository.save(existingReview);
        return strictMapper.map(updated, ProductReviewsReadDTO.class);
    }


    public void deleteReview(Long reviewId) {
        if (!reviewsRepository.existsById(reviewId)) {
            throw new EntityNotFoundException("Review with ID " + reviewId + " not found.");
        }
        reviewsRepository.deleteById(reviewId);
    }


    public CartItemsReadDTO createCartItem(CartItemsCreateDTO createDTO) {

        CartOtherCreateDTO cartDTO = createDTO.getCart();
        ProductsOtherCreateDTO productDTO = createDTO.getProduct();

        if (cartDTO == null) {
            throw new IllegalArgumentException("Cart information is required for item creation.");
        }
        if (cartDTO.getCartId() == null) {
            throw new IllegalArgumentException("CartID is required for cart item creation.");
        }
        if (!cartRepository.existsById(cartDTO.getCartId())) {
            throw new EntityNotFoundException("Cart with cartID " + cartDTO.getCartId() + " not found.");
        }

        if (productDTO == null) {
            throw new IllegalArgumentException("Product information is required for creation.");
        }
        if (productDTO.getProductId() == null) {
            throw new IllegalArgumentException("ProductID is required for creation.");
        }
        if (!productsRepository.existsById(productDTO.getProductId())) {
            throw new EntityNotFoundException("Product with productID " + productDTO.getProductId() + " not found.");
        }

        if (createDTO.getQuantity() == null || createDTO.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity information is needed for cart item creation");
        }
        CartItems cartItemToCreate = defaultMapper.map(createDTO, CartItems.class);
        CartItems cartItemCreated = cartItemsRepository.save(cartItemToCreate);


        Products fullProduct = productsRepository.findById(cartItemCreated.getProduct().getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found after creation"));

        Cart fullCart = cartRepository.findById(cartItemCreated.getCart().getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found after creation"));

        cartItemCreated.setProduct(fullProduct);
        cartItemCreated.setCart(fullCart);

        CartItemsReadDTO responseDto = new CartItemsReadDTO();
        responseDto.setCartItemId(cartItemCreated.getCartItemId());

        ProductsOtherReadDTO productReadDTO = new ProductsOtherReadDTO();
        productReadDTO.setProductId(fullProduct.getProductId());
        productReadDTO.setName(fullProduct.getName());
        productReadDTO.setType(fullProduct.getType());
        productReadDTO.setPrice(fullProduct.getPrice());
        productReadDTO.setImage(fullProduct.getImage());

        CartOtherReadDTO cartReadDTO = new CartOtherReadDTO();
        cartReadDTO.setCartId(fullCart.getCartId());

        responseDto.setProduct(productReadDTO);
        responseDto.setCart(cartReadDTO);
        responseDto.setQuantity(cartItemCreated.getQuantity());
        responseDto.setTotalPrice(fullProduct.getPrice() * cartItemCreated.getQuantity());

        return responseDto;
    }

    public CartItemsReadDTO updateCartItem(CartItemsUpdateDTO updateDTO) {
        if (updateDTO.getCartItemId() != null) {
            Long cartItemID = updateDTO.getCartItemId();
            if (cartItemID != null) {
                Optional<CartItems> optionalCartItem = cartItemsRepository.findById(cartItemID);
                if (optionalCartItem.isPresent()) {
                    CartItems cartItemToUpdate = optionalCartItem.get();
                    Long quantity = updateDTO.getQuantity();
                    if (quantity != null) {
                        cartItemToUpdate.setQuantity(quantity);
                        CartItems cartItemUpdated = cartItemsRepository.save(cartItemToUpdate);
                        CartItemsReadDTO cartItemToShow = strictMapper.map(cartItemUpdated, CartItemsReadDTO.class);
                        cartItemToShow.setTotalPrice(cartItemUpdated.getProduct().getPrice() * cartItemUpdated.getQuantity());
                        return cartItemToShow;
                    } else {
                        throw new IllegalArgumentException("Invalid update request, no final quantity mentioned.");
                    }
                } else {
                    throw new EntityNotFoundException("Cart Item with cartItemId " + cartItemID + " not found.");
                }
            } else {
                throw new IllegalArgumentException("CartItemID information is required for update.");
            }
        } else {
            throw new IllegalArgumentException("CartItem information required for update.");
        }
    }

    public void deleteCartItem(Long cartItemId) {
        if (!cartItemsRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException("Cart item with ID " + cartItemId + " not found.");
        }
        cartItemsRepository.deleteById(cartItemId);
    }

    public void createCart(Users user) {
        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }

    public CartReadDTO getCart(String userUid) {
        Optional<Cart> optionalCart = cartRepository.findByUser_UserUid(userUid);
        if (optionalCart.isPresent()) {
            Cart cartRetrieved = optionalCart.get();
            return strictMapper.map(cartRetrieved, CartReadDTO.class);
        } else {
            throw new EntityNotFoundException("Cart for user with UID " + userUid + " not found");
        }
    }

    public ReceiptDTO processPurchase(Long cartId) throws EntityNotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart with ID " + cartId + " not found"));

        Users user = cart.getUser();
        List<CartItems> cartItems = cart.getCartItems();
        List<PurchaseHistoryReadListDTO> purchaseList = new ArrayList<>();

        ReceiptDTO receipt = new ReceiptDTO();
        receipt.setUser(strictMapper.map(user, UsersOtherReadDTO.class));


        if (cartItems.isEmpty()) {
            receipt.setMessage("Cart is empty! Nothing to Purchase.");
            return receipt;
        }

        for (CartItems item : cartItems) {
            Products product = item.getProduct();
            Long quantity = item.getQuantity();

            // update product stock and purchase count
            Long updatedStock = product.getStock() - quantity;
            if (updatedStock < 0) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }
            product.setStock(updatedStock);
            product.setPurchaseCount(product.getPurchaseCount() + quantity);
            productsRepository.save(product);

            // create purchase history entry
            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setUser(user);
            purchaseHistory.setProduct(product);
            purchaseHistory.setQuantity(quantity);
            purchaseHistory.setTotalPrice(quantity * product.getPrice());
            purchaseHistory.setPurchasedAt(LocalDateTime.now());

            PurchaseHistory saved = purchaseHistoryRepository.save(purchaseHistory);
            PurchaseHistoryReadListDTO purchaseDTO = strictMapper.map(saved, PurchaseHistoryReadListDTO.class);
            purchaseList.add(purchaseDTO);
        }

        cart.getCartItems().clear();
        cartRepository.save(cart);

        receipt.setItems(purchaseList);

        return receipt;
    }

    public List<PurchaseHistoryReadListDTO> getUserPurchaseHistory(String userUid) {
        Users user = usersRepository.findByUserUid(userUid)
                .orElseThrow(() -> new EntityNotFoundException("User with UID " + userUid + " not found"));

        List<PurchaseHistory> historyList = purchaseHistoryRepository.findByUser_UserUid(userUid);
        return historyList.stream()
                .map(history -> strictMapper.map(history, PurchaseHistoryReadListDTO.class))
                .toList();
    }


}
