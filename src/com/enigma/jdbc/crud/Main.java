package com.enigma.jdbc.crud;

import com.enigma.jdbc.dto.request.OrderDetailRequest;
import com.enigma.jdbc.dto.request.OrderRequest;
import com.enigma.jdbc.dto.response.OrderResponse;
import com.enigma.jdbc.repository.OrderRepository;
import com.enigma.jdbc.repository.impl.OrderReporsitoryImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



//        ProductRepository productRepository = new ProductRepositoryImpl();
//        Product nasi = productRepository.saveProduct(new Product(99,"nasi",3000));
//        System.out.println(nasi);
//        long startTime = System.nanoTime();
//
//        List<OrderDetailRequest> orderDetailRequests = List.of(
//                new OrderDetailRequest(1, 6),
//                new OrderDetailRequest(2, 2),
//                new OrderDetailRequest(1, 300)
//        );
        //Scanner scanner = new Scanner(System.in);

        OrderRepository orderRepository = new OrderReporsitoryImpl();
       /* int id = 0;
        while (id<20){
            System.out.print("Masukan Id : ");
            id = scanner.nextInt();
            OrderResponse byId = orderRepository.findById(id);
            System.out.println(byId);
        }*/
        List<OrderResponse> all = orderRepository.findAll();
        System.out.println(all);


//        OrderRequest orderRequest = new OrderRequest(1,1, orderDetailRequests);
//        orderRepository.save(orderRequest);
//
//
//
//        long endTime = System.nanoTime();
//        long duration = (endTime - startTime);  // Durasi dalam nanosekon

        // Konversi ke milidetik (opsional)
//        double milliseconds = duration / 1_000_000.0; // 1 milidetik = 1.000.000 nanosekon
//        System.out.println(duration + " ms");

//
//        // Konversi ke jam, menit, dan detik
//        long milliseconds = duration / 1_000_000; // Ubah ke milidetik
//        long seconds = (milliseconds / 1000) % 60; // Hitung detik
//        long minutes = (milliseconds / (1000 * 60)) % 60; // Hitung menit
//        long hours = (milliseconds / (1000 * 60 * 60)); // Hitung jam
//
//        System.out.println("Durasi eksekusi: " + hours + " jam, " + minutes + " menit, " + seconds + " detik");

    }
}
//       Customer customer = new Customer(
//               12,
//               "Ando",
//               "081400000000",
//               false
//       );
//       CustomerRepository repository = new CustomerRepositoryImpl();
//       //repository.save(customer);
//       /*Scanner scanner = new Scanner(System.in);
//       System.out.println("Please enter your ID: ");
//       int id = scanner.nextInt();*/
//      //Customer cs = repository.findById(id);
//        //System.out.println(cs.toString());
//
//        //Find All
//        //repository.findAll().forEach(System.out::println);
//        //repository.update(customer);
//        //repository.delete(12);
//        Product product = new Product(
//                2,
//                "Lincung",
//                1900
//        );
//        ProductRepository productRepository = new ProductRepositoryImpl();
//        //repository.save(customer);
//        //Product product1 = productRepository.saveProduct(product);
////        System.out.println(product1.toString());
//        Product product1= new Product();
//
//        //product1 = productRepository.findProductById(1);
//        //System.out.println(product1.toString());
//
//        List<Product> products = new ArrayList<>();
//        ProductRepository productRepository1 = new ProductRepositoryImpl();
//
//       // System.out.println(productRepository1.findAllProducts().toString());
//
//        productRepository.deleteProduct(1);
