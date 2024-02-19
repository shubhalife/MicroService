package com.neo.OrderService.respository;

import com.neo.OrderService.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRespository extends JpaRepository<OrderEntity, UUID> {

}
